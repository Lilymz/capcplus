package com.jw.elephant.capcplus.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.dto.server.CverificationDTO;
import com.jw.elephant.capcplus.dto.server.CverificationSearchDTO;
import com.jw.elephant.capcplus.mapper.CverificationMapper;
import com.jw.elephant.capcplus.pojo.Company;
import com.jw.elephant.capcplus.pojo.CverificationAppInfo;
import com.jw.elephant.capcplus.pojo.Server;
import com.jw.elephant.capcplus.pojo.enums.CverificationAppInfoAppStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.CverificationAppInfoCurStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.CverificationAppInfoStatusEnum;
import com.jw.elephant.capcplus.service.CompanyService;
import com.jw.elephant.capcplus.service.CverificationService;
import com.jw.elephant.capcplus.service.ServerService;
import com.jw.elephant.capcplus.vo.cverification.CverificationAppInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 统一认证服务
 * @author zhangjie
 */
@Service
@Slf4j
public class CverificationImpl extends ServiceImpl<CverificationMapper,CverificationAppInfo> implements CverificationService {
    @Resource
    private ServerService serverService;
    @Resource
    private CompanyService companyService;
    /**
     * 分页获取充值记录数据
     * @param dataTableParam 分页参数
     * @param searchDTO 搜索参数
     * @author zhangjie
     * @date 2022/6/6
     * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.Server>
     */
    @Override
    public PageDT<CverificationAppInfoVo> query(DataTableParam dataTableParam, CverificationSearchDTO searchDTO) {
        //构建mybatisPlus所需参数
        Page page = DataTableParam.genPageParam(dataTableParam);
        if (Objects.nonNull(searchDTO)){
            QueryWrapper wrapper = new QueryWrapper();
            if (StringUtils.isNotBlank(searchDTO.getAppName())){
                wrapper.likeRight("app_name",searchDTO.getAppName());
            }
            if (StringUtils.isNotBlank(searchDTO.getAppId())){
                wrapper.eq("app_id",searchDTO.getAppId());
            }
            if (StringUtils.isNotBlank(searchDTO.getAppPlatform())){
                wrapper.eq("app_platform",searchDTO.getAppId());
            }
            if (StringUtils.isNotBlank(searchDTO.getAuditStatus())){
                wrapper.eq("audit_status",searchDTO.getAuditStatus());
            }
            Page<CverificationAppInfo> basePage =  page(page, wrapper);
            Page<CverificationAppInfoVo> voIPage = (Page<CverificationAppInfoVo>)basePage.convert(CverificationAppInfoVo::new);
            attachAttr(voIPage);
            PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
            return pageDT;
        }
        Page<CverificationAppInfo> basePage = page(page);
        Page<CverificationAppInfoVo> voIPage = (Page<CverificationAppInfoVo>)basePage.convert(CverificationAppInfoVo::new);
        attachAttr(voIPage);
        PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
        return pageDT;
    }
    /**
     * 新增应用
     * @param request 请求
     * @param cverificationDTO 新增DTO
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void add(HttpServletRequest request, CverificationDTO cverificationDTO) {
        // 校验数据
        CverificationDTO.check(cverificationDTO);
        CverificationAppInfo appInfoDO = new CverificationAppInfo();
        BeanUtils.copyProperties(cverificationDTO,appInfoDO);
        // 查询企业
        Company company = companyService.getById(Long.valueOf(cverificationDTO.getCompanyId()));
        Assert.isNull(company,"未进行企业申报工作，请先绑定企业！");
        appInfoDO.setUserId(company.getUserId());
        // 应用签名1：表示 采用md进行签名
        appInfoDO.setAppSignType(1);
        appInfoDO.setAppStatus(CverificationAppInfoAppStatusEnum.CLOSE);
        appInfoDO.setAuditStatus(CverificationAppInfoStatusEnum.WAIT);
        appInfoDO.setCurStatus(CverificationAppInfoCurStatusEnum.INVALID);
        appInfoDO.setSystemYwIds(StringUtils.join(cverificationDTO.getSystemYwIds(),","));
        appInfoDO.setAppId(DateUtil.format(new Date(), ThreadLocalRandom.current().nextLong(0,9999)+DateConstant.YYYYMMDDHHMMSS));
        appInfoDO.setCreateTime(new Date());
        save(appInfoDO);
    }
    /**
     * 修改应用
     * @param request 请求
     * @param cverificationDTO 修改DTO
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void modify(HttpServletRequest request, CverificationDTO cverificationDTO) {
        // 校验数据
        CverificationDTO.check(cverificationDTO);
        //查询当前应用
        CverificationAppInfo appInfoDO = getById(Long.valueOf(cverificationDTO.getId()));
        BeanUtils.copyProperties(cverificationDTO,appInfoDO);
        appInfoDO.setAuditStatus(CverificationAppInfoStatusEnum.WAIT);
        updateById(appInfoDO);
    }

    /********************************************************** others ************************************************/

    /**
     *  vo赋值
     *
     * @param basePage
     * @author zhangjie
     * @date 2022/6/7
     * @return void
     */
    private void attachAttr(IPage<CverificationAppInfoVo> basePage) {
        //获取业务ids
        Set<Integer> ywIds = basePage.getRecords().stream().flatMap(vo -> StringUtils.isBlank(vo.getSystemYwIds())?
                Arrays.stream(new String[]{"0"}):Arrays.stream(vo.getSystemYwIds().split(",")))
                .map(Integer::valueOf).collect(Collectors.toSet());
        List<Server> servers = serverService.list(new LambdaQueryWrapper<Server>().in(Server::getId, ywIds));
        final Map<Long, Object> serviceMap = servers.stream().collect(Collectors.toMap(Server::getId, Function.identity(),(k1,k2)->k2));
        //临时容器
        final List<String> names = new ArrayList<>(8);
        basePage.getRecords().forEach(vo->{
            if (StringUtils.isBlank(vo.getSystemYwIds())){
                vo.setSystemYwNames(new ArrayList<>(0));
                return;
            }
            Set<Long> ids = Arrays.stream(vo.getSystemYwIds().split(",")).map(Long::valueOf).collect(Collectors.toSet());
            for (Long id : ids) {
                Server server = (Server)serviceMap.get(id);
                if (Objects.nonNull(server)){
                    names.add(server.getName());
                }
            }
            vo.setSystemYwNames(names);
        });
    }

}
