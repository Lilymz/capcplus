package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.server.detail.ServerDetailSearchDTO;
import com.jw.elephant.capcplus.mapper.ServerDetailMapper;
import com.jw.elephant.capcplus.pojo.CverificationAppInfo;
import com.jw.elephant.capcplus.pojo.Server;
import com.jw.elephant.capcplus.pojo.ServerDetail;
import com.jw.elephant.capcplus.pojo.enums.ServerDetailResultTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.ServerDetailSupplierTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.ServerTypeEnum;
import com.jw.elephant.capcplus.service.CverificationService;
import com.jw.elephant.capcplus.service.ServerDetailService;
import com.jw.elephant.capcplus.service.ServerService;
import com.jw.elephant.capcplus.vo.server.detail.ServerDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务详情 service
 * @author zhangjie
 */
@Service
@Slf4j
public class ServerDetailImpl extends ServiceImpl<ServerDetailMapper, ServerDetail> implements ServerDetailService {
    @Resource
    private ServerService serverService;
    @Resource
    private CverificationService cverificationService;
    /**
     * 获取数据统计列表
     * @param dataTableParam 分页参数
     * @param searchDTO 搜索参数
     * @return
     */
    @Override
    public PageDT<ServerDetailVo> query(DataTableParam dataTableParam, ServerDetailSearchDTO searchDTO) {
        Page page = DataTableParam.genPageParam(dataTableParam);
        if (Objects.nonNull(searchDTO)){
            LambdaQueryWrapper<ServerDetail> wrapper = new LambdaQueryWrapper<>();
            // 搜索条件
            condition(wrapper,searchDTO);
            Page<ServerDetail> basePage =  page(page, wrapper);
            Page<ServerDetailVo> voIPage = (Page<ServerDetailVo>)basePage.convert(ServerDetailVo::new);
            attachAttr(voIPage);
            PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
            return pageDT;
        }
        Page<ServerDetail> basePage =  page(page);
        Page<ServerDetailVo> voIPage = (Page<ServerDetailVo>)basePage.convert(ServerDetailVo::new);
        attachAttr(voIPage);
        PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
        return pageDT;
    }

    /**
     * 查询条件
     * @param wrapper 查询包装
     * @param searchDTO 搜索条件DTO
     */
    private void condition(LambdaQueryWrapper<ServerDetail> wrapper,ServerDetailSearchDTO searchDTO){
        // 流水号
        if (StringUtils.isNotBlank(searchDTO.getSerialNumber())){
            wrapper.eq(ServerDetail::getSerialNumber,searchDTO.getSerialNumber());
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
                wrapper.in(ServerDetail::getServerId,serverIds);
            }
        }
        // 服务类型非空和服务名称空
        if (StringUtils.isNotBlank(searchDTO.getServerType()) &&
                CollectionUtils.isEmpty(searchDTO.getServerName())){
            List<Long> serverIds = serverService.list(new LambdaQueryWrapper<Server>()
                    .select(Server::getId)
                    .eq(Server::getType, ServerTypeEnum.valueOf(searchDTO.getServerType()))
            ).stream().map(Server::getId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(serverIds)){
                wrapper.in(ServerDetail::getServerId,serverIds);
            }
        }
        // 返回类型
        if (StringUtils.isNotBlank(searchDTO.getResultType())&&StringUtils.isNotBlank(searchDTO.getResult())){
            wrapper.eq(ServerDetail::getResultType, ServerDetailResultTypeEnum.valueOf(searchDTO.getResultType()))
                    .eq(ServerDetail::getResult,searchDTO.getResult());
        }
        // 供应商类型
        if (StringUtils.isNotBlank(searchDTO.getSupplierType())){
            wrapper.eq(ServerDetail::getSupplierType, ServerDetailSupplierTypeEnum.valueOf(searchDTO.getSupplierType()));
        }
        // 应用名称
        if (StringUtils.isNotBlank(searchDTO.getAppName())){
            Set<String> appIds = cverificationService.list(new LambdaQueryWrapper<CverificationAppInfo>()
                    .select(CverificationAppInfo::getId)
                    .eq(CverificationAppInfo::getAppName, searchDTO.getAppName())
            ).stream().map(CverificationAppInfo::getAppId).collect(Collectors.toSet());
            wrapper.in(ServerDetail::getAppId,appIds);
        }
        //应用id
        if (StringUtils.isNotBlank(searchDTO.getAppId())){
            Set<String> appIds = cverificationService.list(new LambdaQueryWrapper<CverificationAppInfo>()
                    .select(CverificationAppInfo::getId)
                    .eq(CverificationAppInfo::getAppId, searchDTO.getAppId())
            ).stream().map(CverificationAppInfo::getAppId).collect(Collectors.toSet());
            wrapper.in(ServerDetail::getAppId,appIds);
        }
        //开始时间结束时间
        if (Objects.nonNull(searchDTO.getBeginTime())){
            wrapper.ge(ServerDetail::getCreateTime,searchDTO.getBeginTime());
        }
        if (Objects.nonNull(searchDTO.getEndTime())){
            wrapper.le(ServerDetail::getCreateTime,searchDTO.getEndTime());
        }
    }
    /**
     * vo 赋值
     * @param page 分页数据
     */
    private void attachAttr(Page<ServerDetailVo> page) {
        if (CollectionUtils.isEmpty(page.getRecords())){
            return;
        }
        //获取业务ids
        Set<Long> serverIds = page.getRecords().stream().map(ServerDetailVo::getServerId).collect(Collectors.toSet());
        List<Server> servers = serverService.list(new LambdaQueryWrapper<Server>()
                .select(Server::getId,Server::getName,Server::getType)
                .in(Server::getId, serverIds));
        final Map<Long, String> nameMap = servers.stream().collect(Collectors.toMap(Server::getId, Server::getName, (k1, k2) -> k2));
        final Map<Long, ServerTypeEnum> typeMap = servers.stream().collect(Collectors.toMap(Server::getId, Server::getType, (k1, k2) -> k2));
        //临时容器
        page.getRecords().forEach(vo->{
            vo.setServerName(nameMap.get(vo.getServerId()));
            vo.setServerType(typeMap.get(vo.getServerId()));
        });
    }
}
