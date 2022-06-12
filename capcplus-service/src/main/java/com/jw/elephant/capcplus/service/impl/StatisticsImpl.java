package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.EchartsData;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.statistics.StatisticsSearchDTO;
import com.jw.elephant.capcplus.mapper.StatisticsMapper;
import com.jw.elephant.capcplus.pojo.Server;
import com.jw.elephant.capcplus.pojo.Statistics;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.ServerTypeEnum;
import com.jw.elephant.capcplus.service.ServerService;
import com.jw.elephant.capcplus.service.StatisticsService;
import com.jw.elephant.capcplus.vo.statistics.StatisticsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据统计service
 * @author zhangjie
 */
@Service
@Slf4j
public class StatisticsImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatisticsService {
    @Resource
    private ServerService serverService;

    /**
     * 获取数据统计列表
     * @param dataTableParam
     * @param searchDTO
     * @return
     */
    @Override
    public PageDT<StatisticsVo> query(DataTableParam dataTableParam, StatisticsSearchDTO searchDTO) {
        //构建mybatisPlus所需参数
        Page page = DataTableParam.genPageParam(dataTableParam);
        if (Objects.nonNull(searchDTO)){
            LambdaQueryWrapper<Statistics> condition = new LambdaQueryWrapper<>();
            // 服务类型非空和服务名称空
            if (StringUtils.isNotBlank(searchDTO.getServerType()) &&
                    CollectionUtils.isEmpty(searchDTO.getServerName())){
                List<Long> serverIds = serverService.list(new LambdaQueryWrapper<Server>()
                        .select(Server::getId)
                        .eq(Server::getType, ServerTypeEnum.valueOf(searchDTO.getServerType()))
                ).stream().map(Server::getId).collect(Collectors.toList());
                //服务ids
                if (CollectionUtils.isNotEmpty(serverIds)){
                    condition.in(Statistics::getServerId,serverIds);
                }
            }
            // 服务类型和服务名称非空
           if (StringUtils.isNotBlank(searchDTO.getServerType()) &&
                   CollectionUtils.isNotEmpty(searchDTO.getServerName())){
               List<Long> serverIds = serverService.list(new LambdaQueryWrapper<Server>()
                       .select(Server::getId)
                       .eq(Server::getType, ServerTypeEnum.valueOf(searchDTO.getServerType()))
                       .in(Server::getName, searchDTO.getServerName())
               ).stream().map(Server::getId).collect(Collectors.toList());
               //服务ids
               if (CollectionUtils.isNotEmpty(serverIds)){
                   condition.in(Statistics::getServerId,serverIds);
               }
           }
           //账单日期
           if (Objects.nonNull(searchDTO.getBillDate())){
               condition.le(Statistics::getCreateTime,searchDTO.getBillDate());
           }
            Page<Statistics> basePage =  page(page, condition);
            Page<StatisticsVo> voIPage = (Page<StatisticsVo>)basePage.convert(StatisticsVo::new);
            attachAttr(voIPage);
            PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
            return pageDT;
        }
        Page<Statistics> basePage =  page(page);
        Page<StatisticsVo> voIPage = (Page<StatisticsVo>)basePage.convert(StatisticsVo::new);
        attachAttr(voIPage);
        PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
        return pageDT;
    }

    /**
     * 获取成功率数据
     *
     * @param request 请求
     * @return list 成功率数据
     */
    @Override
    public List<EchartsData> successStatistics(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(TextConstant.USER);
        LambdaQueryWrapper<Statistics> successCountWrapper = new LambdaQueryWrapper<>();
        successCountWrapper.select(Statistics::getSuccessTotal,Statistics::getUnknownTotal,Statistics::getFailTotal)
                .eq(Statistics::getUserId,user.getId());
        List<Statistics> statistics = list(successCountWrapper);
        Long successTotal = statistics.stream().mapToLong(Statistics::getSuccessTotal).sum();
        Long unknownTotal = statistics.stream().mapToLong(Statistics::getUnknownTotal).sum();
        Long failTotal = statistics.stream().mapToLong(Statistics::getFailTotal).sum();
        // 封装数据
        EchartsData successData = new EchartsData("成功使用量总数", successTotal);
        EchartsData unknownData = new EchartsData("未知使用量总数", unknownTotal);
        EchartsData failData = new EchartsData("失败使用量总数", failTotal);
        return Arrays.asList(successData,unknownData,failData);
    }

    /**
     *  vo赋值
     * @param voIPage
     */
    private void attachAttr(Page<StatisticsVo> voIPage) {
        if (CollectionUtils.isEmpty(voIPage.getRecords())){
            return;
        }
        //获取业务ids
        Set<Long> serverIds = voIPage.getRecords().stream().map(StatisticsVo::getServerId).collect(Collectors.toSet());
        List<Server> servers = serverService.list(new LambdaQueryWrapper<Server>().in(Server::getId, serverIds));
        final Map<Long, String> serversMap = servers.stream().collect(Collectors.toMap(Server::getId, Server::getName, (k1, k2) -> k2));
        //临时容器
        voIPage.getRecords().forEach(vo->vo.setServerName(serversMap.get(vo.getServerId())));
    }
}
