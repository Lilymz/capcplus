package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.ticket.TicketApplyDTO;
import com.jw.elephant.capcplus.dto.ticket.TicketInfoSearchDTO;
import com.jw.elephant.capcplus.mapper.TicketInfoMapper;
import com.jw.elephant.capcplus.pojo.Ticket;
import com.jw.elephant.capcplus.pojo.TicketInfo;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.TicketInfoSealTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.TicketInfoStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.TicketInfoTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.TicketStatusEnum;
import com.jw.elephant.capcplus.service.TicketInfoService;
import com.jw.elephant.capcplus.service.TicketService;
import com.jw.elephant.capcplus.vo.ticket.TicketInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 开票与发票记录 service
 * @author zhangjie
 */
@Service
@Slf4j
public class TicketInfoImpl extends ServiceImpl<TicketInfoMapper, TicketInfo> implements TicketInfoService {
    @Resource
    private TicketService ticketService;
    /**
     * 申请发票(审核通过后,已申请过的不可在申请)
     *
     * @param request 请求
     * @param ticketApplyDTO 发票申请DTO
     * @author zhangjie
     * @date 2022/6/9
     * @return void
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void save(HttpServletRequest request, TicketApplyDTO ticketApplyDTO) {
        TicketApplyDTO.check(ticketApplyDTO);
        User user = (User) request.getSession().getAttribute(TextConstant.USER);
        TicketInfo ticketInfoDO = new TicketInfo();
        BeanUtils.copyProperties(ticketApplyDTO,ticketInfoDO);
        ticketInfoDO.setUserId(user.getId());
        ticketInfoDO.setTicketId(Long.valueOf(ticketApplyDTO.getTicketId()));
        Ticket ticket = ticketService.getById(ticketApplyDTO.getTicketId());
        Assert.notTrue(ticket.getStatus().equals(TicketStatusEnum.SUCCESS),"发票信息未审核通过，不能发起发票申请！");
        ticketInfoDO.setTicketType(TicketInfoTypeEnum.valueOf(ticketApplyDTO.getTicketType()));
        ticketInfoDO.setSealType(TicketInfoSealTypeEnum.valueOf(ticketApplyDTO.getSealType()));
        ticketInfoDO.setTicketAddressId(Long.valueOf(ticketApplyDTO.getTicketAddressId()));
        ticketInfoDO.setTicketBalance(new BigDecimal(ticketApplyDTO.getTicketBalance()));
        ticketInfoDO.setStatus(TicketInfoStatusEnum.WAIT);
        ticketInfoDO.setApplyTime(new Date());
        ticketInfoDO.setCreateTime(new Date());
        save(ticketInfoDO);
    }

    /**
     * 获取发票记录
     * @param dataTableParam 分页参数
     * @param request 请求
     * @param ticketInfoSearchDTO  条件DTO
     * @return
     */
    @Override
    public PageDT<TicketInfoVo> query(DataTableParam dataTableParam, HttpServletRequest request, TicketInfoSearchDTO ticketInfoSearchDTO) {
        Page page = DataTableParam.genPageParam(dataTableParam);
        if (Objects.nonNull(ticketInfoSearchDTO)){
            LambdaQueryWrapper<TicketInfo> condition = new LambdaQueryWrapper<>();
            // 条件
            condition(condition,ticketInfoSearchDTO);
            Page<TicketInfo> basePage =  page(page, condition);
            Page<TicketInfoVo> voIPage = (Page<TicketInfoVo>)basePage.convert(TicketInfoVo::new);
            attachAttr(voIPage);
            PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
            return pageDT;
        }
        Page<TicketInfo> basePage =  page(page);
        Page<TicketInfoVo> voIPage = (Page<TicketInfoVo>)basePage.convert(TicketInfoVo::new);
        attachAttr(voIPage);
        PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
        return pageDT;
    }
    /**
     * vo赋值
     * @param voIPage
     * @author zhangjie
     * @date 2022/6/9
     * @return void
     */
    private void attachAttr(Page<TicketInfoVo> voIPage) {
        if (CollectionUtils.isEmpty(voIPage.getRecords())){
            return;
        }
        Set<Long> ticketIds = voIPage.getRecords().stream().map(TicketInfoVo::getTicketId).collect(Collectors.toSet());
        LambdaQueryWrapper<Ticket> ticketWrapper = new LambdaQueryWrapper();
        ticketWrapper.select(Ticket::getId,Ticket::getCreditCode,Ticket::getTicketMan)
                    .in(Ticket::getId,ticketIds);
        List<Ticket> tickets = ticketService.list(ticketWrapper);
        Map<Long, String> manMap = tickets.stream().collect(Collectors.toMap(Ticket::getId, Ticket::getTicketMan, (k1, k2) -> k2));
        Map<Long, String> ticketMap = tickets.stream().collect(Collectors.toMap(Ticket::getId, Ticket::getCreditCode, (k1, k2) -> k2));
        for (TicketInfoVo vo : voIPage.getRecords()) {
            vo.setCreditCode(StringUtils.defaultIfBlank(ticketMap.get(vo.getTicketId()),""));
            vo.setTicketMan(StringUtils.defaultIfBlank(manMap.get(vo.getTicketId()),""));
        }
    }

    /**
     * 搜索条件
     * @param condition 条件
     * @param searchDTO 发票dto
     */
    private void condition(LambdaQueryWrapper<TicketInfo> condition, TicketInfoSearchDTO searchDTO) {
        boolean isTicket = false;
        LambdaQueryWrapper<Ticket> ticketWrapper = new LambdaQueryWrapper();
        ticketWrapper.select(Ticket::getId);
        // 抬头
        if (StringUtils.isNotBlank(searchDTO.getTicketMan())){
            ticketWrapper.likeRight(Ticket::getTicketMan,searchDTO.getTicketMan());
            isTicket =true;
        }
        // 税务登记号
        if (StringUtils.isNotBlank(searchDTO.getCreditCode())){
            ticketWrapper.eq(Ticket::getCreditCode,searchDTO.getCreditCode());
            isTicket =true;
        }
        Ticket ticket = ticketService.getOne(ticketWrapper);
        //输入发票查询条件
        if (Objects.isNull(ticket)&&isTicket){
            condition.eq(TicketInfo::getTicketId,-1);
        }
        if (Objects.nonNull(ticket)){
            condition.eq(TicketInfo::getTicketId,ticket.getId());
        }
        // 发票项目
        if (StringUtils.isNotBlank(searchDTO.getTicketProject())){
            condition.likeRight(TicketInfo::getTicketProject,searchDTO.getTicketProject());
        }
        // 发票类型
        if (StringUtils.isNotBlank(searchDTO.getTicketType())){
            condition.eq(TicketInfo::getTicketType,TicketInfoTypeEnum.valueOf(searchDTO.getTicketType()));
        }
        // 发票审核状态
        if (StringUtils.isNotBlank(searchDTO.getStatus())){
            condition.eq(TicketInfo::getStatus, TicketInfoStatusEnum.valueOf(searchDTO.getStatus()));
        }
    }
}
