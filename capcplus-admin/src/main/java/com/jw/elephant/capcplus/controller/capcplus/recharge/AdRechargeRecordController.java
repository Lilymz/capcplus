package com.jw.elephant.capcplus.controller.capcplus.recharge;

import com.alibaba.fastjson.JSONObject;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.recharge.RechargeRecordPayDTO;
import com.jw.elephant.capcplus.dto.recharge.RechargeRecordSearchDTO;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.pojo.RechargeRecord;
import com.jw.elephant.capcplus.service.RechargeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 充值记录
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping("/capcplus")
public class AdRechargeRecordController {
    @Resource
    private RechargeRecordService service;
    /*************************************************  go  ********************************************************/
    /**
     *  用户中心-充值
     * @author zhangjie
     * @date 2022/5/30
     * @return java.lang.String
     */
    @GetMapping(value = "/go/recharge")
    public String goRecharge(){
        log.info("用户中心-充值");
        return "/view/capcplus/user-center/recharge";
    }
    /**
     *  用户中心-充值记录
     * @author zhangjie
     * @date 2022/5/31
     * @return java.lang.String
     */
    @GetMapping("/go/recharge-list")
    public String goRechargeList(){
        log.info("用户中心-充值记录");
        return "/view/capcplus/user-center/recharge-list";
    }
    /*************************************************  service  ********************************************************/
    /**
     *  分页获取充值记录
     *
     * @param request 当前请求
     * @param response 当前响应
     * @param dataTableParam 请求参数
     * @param searchDTO 搜索条件参数
     * @author zhangjie
     * @date 2022/5/31
     */
    @ControllerLog(value = "用户中心-充值记录")
    @PostMapping(value = "/recharge-list")
    public void reChargeList(HttpServletRequest request, HttpServletResponse response,
                             DataTableParam dataTableParam, RechargeRecordSearchDTO searchDTO) throws IOException {
        try {
            PageDT<RechargeRecord> page = service.query(dataTableParam,searchDTO);
            ControllerHelper.putData(response,page);
        }catch (Exception e){
            log.error("分页获取充值记录失败：",e.getMessage());
            ControllerHelper.putFailData(response,e);
        }
    }
    /**
     *  用户中心-充值记录-支付
     *
     * @param request 当前请求
     * @param response 当前响应
     * @param payDTO 支付DTO
     * @author zhangjie
     * @date 2022/5/31
     * @return void
     */
    @ControllerLog(value = "用户中心-充值记录-支付")
    @PostMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response, RechargeRecordPayDTO payDTO) throws IOException {
        try {
            //暂时没啥处理逻辑
        }catch (Exception ex){
            log.error("充值记录-支付失败：",ex.getMessage());
            ControllerHelper.sendErrorMsgJson2(response,"充值记录-支付失败：",ex);
        }
    }
    /**
     *  用户中心-充值记录-取消支付
     *
     * @param request 当前请求
     * @param response 当前响应
     * @param payDTO 支付DTO
     * @author zhangjie
     * @date 2022/5/31
     * @return void
     */
    @ControllerLog(value = "用户中心-充值记录-取消支付")
    @PostMapping("/cancel-pay")
    public void cancelPay(HttpServletRequest request, HttpServletResponse response, RechargeRecordPayDTO payDTO) throws IOException {
        try {
            boolean cancelPay = service.cancelPay(payDTO);
            JSONObject result = new JSONObject();
            result.put("isSuccess",cancelPay);
            ControllerHelper.successJson(response,result);
        }catch (Exception ex){
            log.error("充值记录-取消支付失败：",ex.getMessage());
            ControllerHelper.sendErrorMsgJson2(response,"充值记录-取消支付失败：",ex);
        }
    }
}
