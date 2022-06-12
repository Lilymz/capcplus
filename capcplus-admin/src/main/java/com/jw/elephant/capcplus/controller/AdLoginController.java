package com.jw.elephant.capcplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.UserLoginDTO;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 大象管理端登录
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping(value ="/admin")
public class AdLoginController {
    @Resource
    UserService userService;
    /**************************************************** go *********************************************/

    @GetMapping(value = "/go/find-account")
    public String goFindPage(){
        return "/view/admin/home/find-account";
    }
    /**
     *  登录页
     *
     * @author zhangjie
     * @date 2022/5/24
     * @return java.lang.String
     */
    @GetMapping(value = "/login")
    public String goLogin(){
        return "view/admin/home/login";
    }
    /*************************************************** supply service ***********************************************/
    /**
     *  大象官网登录
     *
     * @param request 请求
     * @param response 响应
     * @param userLoginDTO 登录DTO
     * @author zhangjie
     * @date 2022/5/25
     */
    @ControllerLog(value = "系统登录-提交登录")
    @PostMapping(value = "/post-login")
    public void login(HttpServletRequest request, HttpServletResponse response, UserLoginDTO userLoginDTO) throws IOException {
        try {
            userService.login(request, response, userLoginDTO);
            ControllerHelper.successJson(response);
        }catch (IncorrectCredentialsException credentialsException){
            ControllerHelper.sendErrorMsgJson(response,"登录失败 用户密码错误");
            log.error("用户登录失败：{}",credentialsException.getMessage());
        }catch(Exception e){
            ControllerHelper.sendErrorMsgJson(response,"登录失败",e);
            log.error("用户登录失败：{}",e.getMessage());
        }
    }
    /**
     *  账号是否存在
     *
     * @param request 请求
     * @param response 响应
     * @param account 当前账号
     * @author zhangjie
     * @date 2022/5/26
     */
    @ControllerLog(value = "系统登录-找回密码-账号验证")
    @PostMapping(value = "/exist-account")
    public void existAccount(HttpServletRequest request, HttpServletResponse response, String account) throws IOException {
        try {
            boolean isExist = userService.existAccount(request, response, account);
            JSONObject result = new JSONObject();
            result.put("isExist",isExist);
            ControllerHelper.successJson(response,result);
        }catch(Exception e){
            ControllerHelper.sendErrorMsgJson2(response,"数据查询失败",e);
            log.error("数据查询失败：{}",e.getMessage());
        }
    }
    /**
     *  发送邮件
     *
     * @param request 请求
     * @param response 响应
     * @param account 当前账号
     * @param type 当前类型:1发送验证码，2校验验证码
     * @param code 当前输入验证码
     * @author zhangjie
     * @date 2022/5/26
     */
    @ControllerLog(value = "系统登录-找回密码-发送邮件")
    @PostMapping(value = "/send-email")
    public void sendEmail(HttpServletRequest request, HttpServletResponse response,
                          String account,String email,Integer type,String code) throws IOException {
        try {
            JSONObject result = new JSONObject();
            // 类型:1发送验证码，2校验验证码
            switch (type){
                case 1:
                    Long userId = userService.sendEmail(request, response, account,email);
                    result.put("isSuccess",true);
                    //防止前端的Number类型长度限制，会被截断
                    result.put("userId",userId.toString());
                    break;
                case 2:
                    Assert.isBlank(code,"请输入验证码！");
                    // 校验当前验证码
                    String sessionCode = (String)request.getSession().getAttribute(TextConstant.EMAIL_CODE);
                    if (code.equals(sessionCode)){
                        // 验证通过后移除
                        request.getSession().removeAttribute(TextConstant.EMAIL_CODE);
                    }
                    result.put("isCorrect",code.equals(sessionCode));
                    break;
                default:
                    break;
            }
            ControllerHelper.successJson(response,result);
        }catch(Exception e){
            ControllerHelper.sendErrorMsgJson2(response,"发送邮件失败",e);
            log.error("发送邮件失败：{}",e.getMessage());
        }
    }
    /**
     *  修改密码
     *
     * @param request 请求
     * @param response 响应
     * @param account 当前账号
     * @author zhangjie
     * @date 2022/5/26
     */
    @ControllerLog(value = "系统登录-找回密码-修改密码")
    @PostMapping(value = "/change-password")
    public void changePassword(HttpServletRequest request, HttpServletResponse response, Long id,
                               String password,String againPassword) throws IOException {
        try {
            boolean isSuccess = userService.changePassWord(request, response, id,password,againPassword);
            JSONObject result = new JSONObject();
            result.put("isSuccess",isSuccess);
            ControllerHelper.successJson(response,result);
        }catch(Exception e){
            ControllerHelper.sendErrorMsgJson2(response,"修改密码失败",e);
            log.error("修改密码失败：{}",e.getMessage());
        }
    }
}
