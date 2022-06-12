package com.jw.elephant.capcplus.controller.capcplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.index.CheckStepDTO;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.pojo.Company;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.service.CompanyService;
import com.jw.elephant.capcplus.service.UserService;
import com.jw.elephant.capcplus.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 系统后台首页
 *
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping("/capcplus")
public class AdIndexController {
    @Resource
    private UserService userService;
    @Resource
    private CompanyService companyService;
    /********************************************** go ******************************************/
    /**
     * 用户中心-首页
     * @param request
     * @param model
     * @return
     */
    @GetMapping(value = "/go/index")
    public String index(HttpServletRequest request,Model model){
        // 当前用户
        model.addAttribute(TextConstant.USER,request.getSession().getAttribute("user"));
        return "view/capcplus/user-center/index";
    }
    /**
     *  用户中心-企业申请-企业资质申请
     * @author zhangjie
     * @date 2022/5/30
     * @return java.lang.String
     */
    @GetMapping(value = "/go/qulification-apply")
    public String goApply(HttpServletRequest request,Model model){
        //当前用户企业相关数据,没有查到说明是未提交企业信息
        User user = (User)request.getSession().getAttribute("user");
        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<Company>().eq(Company::getUserId, user.getId());
        Company company = companyService.getOne(wrapper);
        model.addAttribute("company",company);
        log.info("用户中心-企业申请-企业资质申请");
        return "view/capcplus/user-center/qulification-apply";
    }
    @GetMapping(value = "/go/add-company")
    public String addCompany(){
        return "/view/capcplus/user-center/add-company";
    }
    /**
     *  用户中心-企业申请-企业资质申请-企业信息
     * @author zhangjie
     * @date 2022/5/30
     * @return java.lang.String
     */
    @GetMapping(value = "/go/enterprise-info")
    public String goEnterpriseInfo(HttpServletRequest request,Model model){
        //当前用户企业相关数据,没有查到说明是未提交企业信息
        User user = (User)request.getSession().getAttribute("user");
        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<Company>().eq(Company::getUserId, user.getId());
        Company company = companyService.getOne(wrapper);
        model.addAttribute("company",company);
        log.info("用户中心-企业申请-企业资质申请-企业信息");
        return "view/capcplus/user-center/enterprise-info";
    }
    /****************************************** service *************************************/
    /**
     *  用户中心-预警调整
     *
     * @param request 请求
     * @param response 响应
     * @param balanceWarning 预警余额
     * @author zhangjie
     * @date 2022/5/30
     * @return void
     */
    @RequiresPermissions("user-center-balance-warning")
    @ControllerLog(value = "用户中心-预警调整")
    @PostMapping(value = "/balance-warning")
    public void balanceWarning(HttpServletRequest request, HttpServletResponse response,String balanceWarning) throws IOException {
        try {
            String ipAddress = IpUtil.getIpAddress(request);
            Assert.isBlank(balanceWarning,"请输入1-1994区间预警余额");
            BigDecimal warnBalance = new BigDecimal(balanceWarning);
            Assert.isTrue(warnBalance.compareTo(new BigDecimal(1994))==1,"预警余额不能超过1994");
            Assert.isTrue(warnBalance.compareTo(new BigDecimal(1))<0,"新设置预警余额不能小于1");
            User user = (User) request.getSession().getAttribute(TextConstant.USER);
            user.setBalanceWarn(warnBalance);
            userService.updateById(user);
        }catch (Exception e){
            log.error("预警调整失败：",e.getMessage());
            ControllerHelper.sendErrorMsgJson(response,"预警调整失败：",e);
        }
    }

    /**
     *  用户中心-绑定企业信息第一步
     *
     * @param request 请求
     * @param response 响应
     * @param checkStepOneDTO 校验信息
     * @author zhangjie
     * @date 2022/6/6
     * @return void
     */
    @ControllerLog(value = "用户中心-绑定企业信息")
    @PostMapping(value = "/check-step-one")
    public void checkStepOne(HttpServletRequest request, HttpServletResponse response, CheckStepDTO checkStepOneDTO) throws IOException {
        try {
            CheckStepDTO.check(checkStepOneDTO);
            ControllerHelper.successJson(response);
        }catch (Exception e){
            log.error("绑定企业信息失败：",e.getMessage());
            ControllerHelper.sendErrorMsgJson(response,"绑定企业信息失败：",e);
        }
    }
    /**
     *  用户中心-绑定企业信息第二步
     *
     * @param request 请求
     * @param response 响应
     * @param checkStepDTO 校验信息
     * @author zhangjie
     * @date 2022/6/6
     * @return void
     */
    @ControllerLog(value = "用户中心-绑定企业信息")
    @PostMapping(value = "/check-step-two")
    public void checkStepTwo(HttpServletRequest request, HttpServletResponse response, CheckStepDTO checkStepDTO) throws IOException {
        try {
            companyService.add(request,checkStepDTO);
            ControllerHelper.successJson(response);
        }catch (Exception e){
            log.error("绑定企业信息失败：",e.getMessage());
            ControllerHelper.sendErrorMsgJson(response,"绑定企业信息失败：",e);
        }
    }
}
