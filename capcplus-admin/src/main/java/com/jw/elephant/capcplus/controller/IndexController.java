package com.jw.elephant.capcplus.controller;

import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.constant.ApiConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 大象官网
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping
public class IndexController {


    /******************************************** go *****************************************/
    /**
     *  首页
     *
     * @author zhangjie
     * @date 2022/5/23
     * @return java.lang.String
     */
    @GetMapping(value = "")
    public String goIndex(){
        return "/view/admin/home/index";
    }
    /**
     *  大象认证页
     *
     * @author zhangjie
     * @date 2022/5/24
     * @return java.lang.String
     */
    @GetMapping(value = "/cverify/go/detail")
    public String goCverifyDetailInfo(){
        return "/view/admin/home/cverify";
    }
    @GetMapping(value = "/apiFrame/go/detail")
    public String goApiFrameDetail(){
        return "view/admin/home/api-frame";
    }
    /**
     *  api文档页
     *
     * @author zhangjie
     * @date 2022/5/24
     * @return java.lang.String
     */
    @GetMapping(value = "/api/doc/go/detail")
    public String goApiDetail(){
        return "view/admin/home/api-doc";
    }

    /**
     * 获取api文档
     *
     * @param request
     * @param response
     * @param type
     * @author zhangjie
     * @date 2022/5/25
     */
    @ControllerLog(value = "api说明-获取api文档")
    @PostMapping(value = "/api/doc/data/{type}")
    public void getApiData(HttpServletRequest request, HttpServletResponse response, @PathVariable String type){
        try {
            if (ApiConstant.Type.PHONE_CHECK.equals(type)){
                //获取手机号校验文档数据(目前暂时不用查看数据库)

            }
        }catch (Exception e){
            log.error("获取文档失败：{}",e.getMessage());
        }
    }
}
