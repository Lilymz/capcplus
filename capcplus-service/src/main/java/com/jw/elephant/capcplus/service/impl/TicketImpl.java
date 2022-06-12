package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.ticket.TicketDTO;
import com.jw.elephant.capcplus.mapper.TicketMapper;
import com.jw.elephant.capcplus.pojo.Ticket;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.TicketStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.TicketTypeEnum;
import com.jw.elephant.capcplus.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 开票 service
 * @author zhangjie
 */
@Service
@Slf4j
public class TicketImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {
    /**
     *  修改开票信息
     * @param request 请求
     * @param ticketDTO 开票DTO
     * @author zhangjie
     * @date 2022/6/8
     * @return void
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveOrUpdate(HttpServletRequest request,TicketDTO ticketDTO) {
        // 校验
        TicketDTO.check(ticketDTO);
        Ticket currentDO = getOne(new LambdaQueryWrapper<Ticket>().eq(Ticket::getId, ticketDTO.getIdStr()));
        // 数据是否未改动
        TicketDTO.isChange(currentDO,ticketDTO);
        User user = (User) request.getSession().getAttribute(TextConstant.USER);
        Ticket ticketDO =new Ticket();
        BeanUtils.copyProperties(ticketDTO,ticketDO);
        ticketDO.setStatus(TicketStatusEnum.WAIT);
        ticketDO.setType(TicketTypeEnum.valueOf(ticketDTO.getTypeEnum()));
        ticketDO.setId(StringUtils.isBlank(ticketDTO.getIdStr())?null:Long.valueOf(ticketDTO.getIdStr()));
        if (StringUtils.isBlank(ticketDTO.getIdStr())){
            ticketDO.setUserId(user.getId());
            ticketDO.setCreateTime(new Date());
        }
        saveOrUpdate(ticketDO);
    }
}
