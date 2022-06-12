package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.server.ServerSearchDTO;
import com.jw.elephant.capcplus.mapper.ServerMapper;
import com.jw.elephant.capcplus.pojo.Company;
import com.jw.elephant.capcplus.pojo.Server;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.CompanyAuditStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.CompanyStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.ServerStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.UserStatusEnum;
import com.jw.elephant.capcplus.service.CompanyService;
import com.jw.elephant.capcplus.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static java.lang.String.valueOf;

/**
 * 服务 service
 * @author zhangjie
 */
@Service
@Slf4j
public class ServerImpl extends ServiceImpl<ServerMapper, Server> implements ServerService {
    @Resource
    private ServerMapper serverMapper;
    @Resource
    private CompanyService companyService;
    /**
     *   分页获取充值记录数据
     *
     * @param dataTableParam 分页参数
     * @param searchDTO 搜索参数
     * @author zhangjie
     * @date 2022/5/31
     * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.RechargeRecord>
     */
    @Override
    public PageDT<Server> query(DataTableParam dataTableParam, ServerSearchDTO searchDTO) {
        //构建mybatisPlus所需参数
        Page page = DataTableParam.genPageParam(dataTableParam);
        if (Objects.nonNull(searchDTO)){
            QueryWrapper wrapper = new QueryWrapper();
            if (StringUtils.isNotBlank(searchDTO.getServerName())){
                wrapper.likeRight("name",searchDTO.getServerName());
            }
            if (Objects.nonNull(searchDTO.getServerType())){
                if (searchDTO.getServerType().equals(0)){
                    wrapper.ge("type",searchDTO.getServerType());
                }else{
                    wrapper.eq("type",searchDTO.getServerType());
                }
            }
            Page basePage = page(page, wrapper);
            PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
            return pageDT;
        }
        Page basePage = page(page);
        PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
        return pageDT;
    }
    /**
     * 开启或关闭当前服务
     *
     * @param id 服务id
     * @param status 服务状态
     * @author zhangjie
     * @date 2022/6/1
     * @return boolean
     */
    @Override
    public boolean closeOpen(Long id,String status) {
        Assert.isNull(id,"未获取到当前服务id！");
        Server server = serverMapper.selectById(id);
        Assert.isNull(server,"当前服务不存在！");
        Assert.isTrue(status.equals(server.getStatus()),"已"+status);
        server.setStatus(ServerStatusEnum.valueOf(status));
        serverMapper.updateById(server);
        return true;
    }
    /**
     *  重置服务密码
     * @param id 服务id
     * @author zhangjie
     * @date 2022/6/1
     * @return boolean
     */
    @Override
    public String resetPassword(Long id) {
        Assert.isNull(id,"未获取到当前服务id！");
        Server server = serverMapper.selectById(id);
        Assert.isNull(server,"当前服务不存在！");
        // 重置密码
        String pwd = valueOf(RandomUtils.nextInt(12634525, 99999999));
        server.setYwPwd(pwd);
        serverMapper.updateById(server);
        return pwd;
    }
    /**
     *  检验：首先要先检查用户状态、企业审核状态、企业有效状态，检查通过才能查询统一认证列表信息。不通过暂时调列表页
     *
     * @param request 请求
     * @author zhangjie
     * @date 2022/6/6
     * @return boolean 是否通过
     */
    @Override
    public boolean checkCverifycation(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(TextConstant.USER);
        // 用户状态
        if (UserStatusEnum.DISABLED.equals(user.getStatus())){
            return false;
        }
        Company company = companyService.getOne(new LambdaQueryWrapper<Company>().eq(Company::getUserId, user.getId()));
        if (Objects.isNull(company)){
            return false;
        }
        //企业审核状态
        if (!CompanyAuditStatusEnum.SUCCESS.equals(company.getAuditStatus())){
            return false;
        }
        //企业有效状态
        if (!CompanyStatusEnum.NORMAL.equals(company.getCompanyStatus())){
            return false;
        }
        return true;
    }
}
