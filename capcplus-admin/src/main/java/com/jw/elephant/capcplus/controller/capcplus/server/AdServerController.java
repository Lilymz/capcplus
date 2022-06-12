package com.jw.elephant.capcplus.controller.capcplus.server;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.server.CverificationDTO;
import com.jw.elephant.capcplus.dto.server.CverificationSearchDTO;
import com.jw.elephant.capcplus.dto.server.ServerSearchDTO;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.pojo.Company;
import com.jw.elephant.capcplus.pojo.Server;
import com.jw.elephant.capcplus.pojo.SysConfig;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.SysConfigAscriptionTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.SysConfigTypeEnum;
import com.jw.elephant.capcplus.service.CompanyService;
import com.jw.elephant.capcplus.service.CverificationService;
import com.jw.elephant.capcplus.service.ServerService;
import com.jw.elephant.capcplus.service.SysConfigService;
import com.jw.elephant.capcplus.vo.cverification.CverificationAppInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务管理模块
 *
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping("/capcplus/server")
public class AdServerController {
    @Resource
    private ServerService service;
    @Resource
    private CverificationService cverificationService;
    @Resource
    private CompanyService companyService;
    @Resource
    private SysConfigService sysConfigService;
    /**************************************************** go **********************************************/
    /**
     *  去服务列表
     * @author zhangjie
     * @date 2022/6/1
     * @return java.lang.String
     */
    @GetMapping("/go/list")
    public String goList(){
        return "/view/capcplus/server/server-list";
    }
    /**
     *  去统一认证(检查相关信息)
     *
     * @author zhangjie
     * @date 2022/6/1
     * @return java.lang.String
     */
    @GetMapping("/go/cverifycation")
    public String goCverifycation(HttpServletRequest request, Model model){
        boolean isOk =  service.checkCverifycation(request);
        if (!isOk){
            return "/view/capcplus/server/server-list";
        }
        //获取当前所有服务
        List<Server> servers = service.list();
        //去重
        servers = servers.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(Server::getName))), ArrayList::new));
        User user = (User)request.getSession().getAttribute(TextConstant.USER);
        Company company = companyService.getOne(new LambdaQueryWrapper<Company>().eq(Company::getUserId, user.getId()));
        model.addAttribute("company",company);
        model.addAttribute("servers",servers);
        //白名单
        List<SysConfig> whiteList = sysConfigService.list(new LambdaQueryWrapper<SysConfig>()
                .eq(SysConfig::getType, SysConfigTypeEnum.WHITE_LIST)
                .eq(SysConfig::getAscriptionType, SysConfigAscriptionTypeEnum.USER)
                .eq(SysConfig::getAscriptionId, company.getUserId()));
        List<String> IPList = whiteList.stream().map(SysConfig::getValue).collect(Collectors.toList());
        model.addAttribute("whiteList", StringUtils.join(IPList,","));
        return "/view/capcplus/server/cverifycation";
    }

    /**************************************************** service ******************************************/
    /**
     *  服务管理-统一认证-获取统一认证列表
     *
     * @param request 请求
     * @param response 响应
     * @param dataTableParam 分页参数
     * @param searchDTO 查询参数
     * @author zhangjie
     */
    @ControllerLog("服务管理-统一认证-获取统一认证列表")
    @GetMapping("/cverification-list")
    public void cverificationList(HttpServletRequest request, HttpServletResponse response,
                     DataTableParam dataTableParam, CverificationSearchDTO searchDTO) throws IOException {
        try {
            PageDT<CverificationAppInfoVo> page = cverificationService.query(dataTableParam, searchDTO);
            ControllerHelper.putData(response,page);
        }catch (Exception ex){
            log.error("获取统一认证列表失败：",ex);
            ControllerHelper.putFailData(response,ex);
        }
    }
    /**
     *  服务管理-统一认证-配置白名单
     *
     * @param request 请求
     * @param response 响应
     * @param whiteList 白名单数据
     * @author zhangjie
     */
    @ControllerLog("服务管理-统一认证-配置白名单")
    @GetMapping("/white-list")
    public void whiteList(HttpServletRequest request, HttpServletResponse response, String whiteList) throws IOException {
        try {
            sysConfigService.add(request,whiteList);
            ControllerHelper.successJson(response);
        }catch (Exception ex){
            log.error("配置白名单失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"配置白名单失败：",ex);
        }
    }
    /**
     * 服务管理-统一认证-新增认证
     *
     * @param request 请求
     * @param response 响应
     * @param cverificationDTO 新增数据
     * @author zhangjie
     * @date 2022/6/7
     * @return void
     */
    @ControllerLog("服务管理-统一认证-新增认证")
    @PostMapping("/add-cverification")
    public void addCverification(HttpServletRequest request, HttpServletResponse response, CverificationDTO cverificationDTO) throws IOException {
        try {
            cverificationService.add(request,cverificationDTO);
            ControllerHelper.successJson(response);
        }catch (Exception ex){
            log.error("新增认证失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"新增认证失败：",ex);
        }
    }
    /**
     * 服务管理-统一认证-修改认证
     *
     * @param request 请求
     * @param response 响应
     * @param cverificationDTO 修改数据
     * @author zhangjie
     * @date 2022/6/7
     * @return void
     */
    @ControllerLog("服务管理-统一认证-修改认证")
    @PostMapping("/modify-cverification")
    public void modifyCverification(HttpServletRequest request, HttpServletResponse response, CverificationDTO cverificationDTO) throws IOException {
        try {
            cverificationService.modify(request,cverificationDTO);
            ControllerHelper.successJson(response);
        }catch (Exception ex){
            log.error("修改认证失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"修改认证失败：",ex);
        }
    }
    /**
     * 服务管理-统一认证-删除认证
     *
     * @param request 请求
     * @param response 响应
     * @author zhangjie
     * @date 2022/6/7
     * @return void
     */
    @ControllerLog("服务管理-统一认证-删除认证")
    @PostMapping("/del-cverification")
    public void delCverification(HttpServletRequest request, HttpServletResponse response,String appIds) throws IOException {
        try {
            cverificationService.removeBatchByIds(Arrays.asList(appIds.split(",")));
            ControllerHelper.successJson(response);
        }catch (Exception ex){
            log.error("删除认证失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"删除认证失败：",ex);
        }
    }
    /**
     *  服务管理-获取服务列表
     *
     * @param request 请求
     * @param response 响应
     * @param dataTableParam 分页参数
     * @param searchDTO 查询参数
     * @author zhangjie
     */
    @ControllerLog("服务管理-获取服务列表")
    @GetMapping("/server-list")
    public void list(HttpServletRequest request, HttpServletResponse response,
                     DataTableParam dataTableParam, ServerSearchDTO searchDTO) throws IOException {
        try {
            PageDT<Server> page = service.query(dataTableParam, searchDTO);
            ControllerHelper.putData(response,page);
        }catch (Exception ex){
            log.error("获取服务列失败：",ex);
            ControllerHelper.putFailData(response,ex);
        }
    }
    /**
     *  服务管理-关闭或者启用服务
     *
     * @param request
     * @param response
     * @author zhangjie
     * @date 2022/6/1
     * @return void
     */
    @ControllerLog("服务管理-关闭或者启用服务")
    @PostMapping("/close-open-server")
    public void closeOpen(HttpServletRequest request, HttpServletResponse response,Long id,String status) throws IOException {
        try {
            service.closeOpen(id, status);
            ControllerHelper.successJson(response);
        }catch (Exception ex){
            log.error("关闭或者启用服务失败：",ex);
            ControllerHelper.sendErrorMsgJson2(response,"关闭或者启用服务失败:",ex);
        }
    }
    /**
     *  服务管理-重置密码
     *
     * @param request
     * @param response
     * @author zhangjie
     * @date 2022/6/1
     * @return void
     */
    @ControllerLog("服务管理-重置密码")
    @PostMapping("/reset-pwd")
    public void closeOpen(HttpServletRequest request, HttpServletResponse response,Long id) throws IOException {
        try {
            JSONObject result = new JSONObject();
            String resetPassword = service.resetPassword(id);
            result.put("pwd",resetPassword);
            result.put("code","0000");
            ControllerHelper.successJson(response,result);
        }catch (Exception ex){
            log.error("重置密码失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"重置密码失败:",ex);
        }
    }
}
