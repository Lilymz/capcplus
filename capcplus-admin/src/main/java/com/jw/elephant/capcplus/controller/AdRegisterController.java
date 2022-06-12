package com.jw.elephant.capcplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.dto.UserRegisterDTO;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdRegisterController {
    @Resource
    private UserService userService;
    /**************************************** go *************************************/
    /**
     *  去注册页
     *
     * @author zhangjie
     * @date 2022/5/26
     */
    @GetMapping("/go/register")
    public String goRegister(){
        log.info("系统注册-去注册");
        return "/view/admin/home/register";
    }

    /**
     *  大象官网登录
     *
     * @param request 请求
     * @param response 响应
     * @param userLoginDTO 登录DTO
     * @author zhangjie
     * @date 2022/5/25
     */
    @ControllerLog(value = "系统注册-提交注册")
    @PostMapping(value = "/post-register")
    public void login(HttpServletRequest request, HttpServletResponse response, UserRegisterDTO userRegisterDTO) throws IOException {
        try {
            userService.register(request, response, userRegisterDTO);
            JSONObject result =new JSONObject();
            result.put("url","/admin/login");
            ControllerHelper.successJson2(response,result);
        }catch (Exception e){
            ControllerHelper.sendErrorMsgJson2(response,"注册失败",e);
            log.error("用户注册失败：{}",e.getMessage());
        }
    }
}
