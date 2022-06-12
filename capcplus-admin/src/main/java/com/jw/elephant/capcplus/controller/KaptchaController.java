package com.jw.elephant.capcplus.controller;

import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.service.KaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

@Controller
@Slf4j
@RequestMapping("/img")
public class KaptchaController {

    @Resource
    private KaptchaService kaptchaService;
    /**
     *  获取图片验证码（文字或者数字）
     *
     * @param request 当前请求request
     * @param response 当前响应response
     * @param timeStamp 当前时间戳
     * @author zhangjie
     * @date 2022/5/20
     */
    @ControllerLog(value = "验证码服务-获取验证码")
    @GetMapping(value = "/code")
    public void code(HttpServletRequest request,HttpServletResponse response){
        try {
            ByteArrayOutputStream outImage =kaptchaService.getCode(request);
            ControllerHelper.successImage(response,outImage.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取验证码失败：{}",e.getMessage());
        }
    }
}
