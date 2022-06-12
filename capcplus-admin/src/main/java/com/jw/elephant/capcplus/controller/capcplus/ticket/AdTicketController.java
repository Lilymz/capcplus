package com.jw.elephant.capcplus.controller.capcplus.ticket;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.address.AddressDTO;
import com.jw.elephant.capcplus.dto.ticket.TicketApplyDTO;
import com.jw.elephant.capcplus.dto.ticket.TicketDTO;
import com.jw.elephant.capcplus.dto.ticket.TicketInfoSearchDTO;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.pojo.*;
import com.jw.elephant.capcplus.pojo.enums.RechargeRecordOrderStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.RechargeRecordPayStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.TicketInfoStatusEnum;
import com.jw.elephant.capcplus.service.*;
import com.jw.elephant.capcplus.vo.ticket.TicketAddressVo;
import com.jw.elephant.capcplus.vo.ticket.TicketInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 开票管理
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping("/capcplus/ticket")
public class AdTicketController {
    @Resource
    private TicketService ticketService;
    @Resource
    private TicketAddressService ticketAddressService;
    @Resource
    private ServerService serverService;
    @Resource
    private RechargeRecordService rechargeRecordService;
    @Resource
    private TicketInfoService ticketInfoService;
    /************************************************** go  ******************************************/
    /**
     *  开票管理-开票信息
     * @author zhangjie
     * @date 2022/6/1
     * @return java.lang.String
     */
    @GetMapping("/go/ticket-info")
    public String goTicketInfo(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute(TextConstant.USER);
        Ticket ticket = ticketService.getOne(new LambdaQueryWrapper<Ticket>().eq(Ticket::getUserId, user.getId()));
        model.addAttribute("ticket",ticket);
        return "/view/capcplus/ticket/ticket-info";
    }
    /**
     *  开票管理-地址管理
     * @author zhangjie
     * @date 2022/6/1
     * @return java.lang.String
     */
    @GetMapping("/go/address-list")
    public String goAddressList(){
        return "/view/capcplus/ticket/address-list";
    }
    /**
     *  开票管理-发票申请
     * @author zhangjie
     * @date 2022/6/1
     * @return java.lang.String
     */
    @GetMapping("/go/ticket-apply")
    public String goTicketApply(HttpServletRequest request,Model model){
        User user = (User)request.getSession().getAttribute(TextConstant.USER);
        // 获取当前所有服务类型
        List<Server> servers = serverService.list(null);
        Ticket ticket = ticketService.getOne(new LambdaQueryWrapper<Ticket>().eq(Ticket::getUserId, user.getId()));
        // 账单
        LambdaQueryWrapper<RechargeRecord> recordWrapper = new LambdaQueryWrapper<RechargeRecord>()
                .eq(RechargeRecord::getUserId,user.getId())
                .eq(RechargeRecord::getOrderStatus, RechargeRecordOrderStatusEnum.SUCCESS)
                .eq(RechargeRecord::getPayStatus, RechargeRecordPayStatusEnum.SUCCESS);
        List<RechargeRecord> bills = rechargeRecordService.list(recordWrapper);
        model.addAttribute("ticket",ticket);
        model.addAttribute("servers",servers);
        model.addAttribute("bills",bills);
        return "/view/capcplus/ticket/ticket-apply";
    }
    /**
     *  开票管理-发票记录
     * @author zhangjie
     * @date 2022/6/1
     * @return java.lang.String
     */
    @GetMapping("/go/ticket-list")
    public String goTicketList(){
        return "/view/capcplus/ticket/ticket-list";
    }
    /************************************************* service ***********************************/
    /**
     * 开票管理-发票审核撤销
     *
     * @param request 请求
     * @param response 响应
     * @param id 发票记录id
     * @author zhangjie
     * @date 2022/6/8
     * @return void
     */
    @ControllerLog(value = "开票管理-发票审核撤销")
    @PostMapping(value = "/ticket-cancel")
    public void ticketCancel(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        try {
            TicketInfo infoDO = ticketInfoService.getById(id);
            Assert.isNull(infoDO,"未找到当前发票申请记录");
            infoDO.setStatus(TicketInfoStatusEnum.CANCEL);
            ticketInfoService.updateById(infoDO);
            ControllerHelper.successJson(response);
        }catch (Exception exception){
            log.error("发票审核撤销失败：",exception);
            ControllerHelper.sendErrorMsgJson(response,"发票审核撤销失败：",exception);
        }
    }
    /**
     * 获取发票记录列表
     *
     * @param request 请求
     * @param response 响应
     * @param id 发票记录id
     * @throws IOException
     */
    @ControllerLog(value = "开票管理-获取发票记录列表")
    @PostMapping(value = "/ticket-detail")
    public void ticketDetail(HttpServletRequest request, HttpServletResponse response,String id) throws IOException {
        try {
            JSONObject result = new JSONObject();
            // 开票内容
            TicketInfo ticketInfo = ticketInfoService.getById(id);
            // 开票信息
            Ticket ticket = ticketService.getById(ticketInfo.getTicketId());
            // 收票地址
            TicketAddress ticketAddress = ticketAddressService.getById(ticketInfo.getTicketAddressId());
            Set<String> billIds = Arrays.stream(ticketInfo.getBillIds().split(",")).collect(Collectors.toSet());
            // 账单
            List<RechargeRecord> bills = rechargeRecordService.listByIds(billIds);
            result.put("ticketInfo",ticketInfo);
            result.put("ticket",ticket);
            result.put("ticketAddress",ticketAddress);
            result.put("bills",bills);
            result.put("code",0000);
            ControllerHelper.successJson(response,result);
        }catch (Exception ex){
            log.error("获取发票记录列表失败：",ex);
            ControllerHelper.putFailData(response,ex);
        }
    }
    /**
     * 获取发票记录列表
     *
     * @param request 请求
     * @param response 响应
     * @param dataTableParam 分页参数
     * @param ticketInfoSearchDTO 条件dto
     * @throws IOException
     */
    @ControllerLog(value = "开票管理-获取发票记录列表")
    @PostMapping(value = "/ticket-list")
    public void ticketList(HttpServletRequest request, HttpServletResponse response, DataTableParam dataTableParam
            , TicketInfoSearchDTO ticketInfoSearchDTO) throws IOException {
        try {
            PageDT<TicketInfoVo> page = ticketInfoService.query(dataTableParam,request,ticketInfoSearchDTO);
            ControllerHelper.putData(response,page);
        }catch (Exception ex){
            log.error("获取发票记录列表失败：",ex);
            ControllerHelper.putFailData(response,ex);
        }
    }
    /**
     * 开票管理-发票申请
     *
     * @param request 请求
     * @param response 响应
     * @param ticketApplyDTO 发票申请DTO
     * @author zhangjie
     * @date 2022/6/8
     * @return void
     */
    @ControllerLog(value = "开票管理-发票申请")
    @PostMapping(value = "/ticket-apply")
    public void ticketApply(HttpServletRequest request, HttpServletResponse response, TicketApplyDTO ticketApplyDTO) throws IOException {
        try {
            ticketInfoService.save(request,ticketApplyDTO);
            ControllerHelper.successJson(response);
        }catch (Exception exception){
            log.error("发票申请失败：",exception);
            ControllerHelper.sendErrorMsgJson(response,"发票申请失败：",exception);
        }
    }
    /**
     * 开票管理-修改开票信息
     *
     * @param request 请求
     * @param response 响应
     * @param ticketDTO 开票DTO
     * @author zhangjie
     * @date 2022/6/8
     * @return void
     */
    @ControllerLog(value = "开票管理-修改开票信息")
    @PostMapping(value = "/update-ticket")
    public void updateTicket(HttpServletRequest request, HttpServletResponse response, TicketDTO ticketDTO) throws IOException {
        try {
            ticketService.saveOrUpdate(request,ticketDTO);
            ControllerHelper.successJson(response);
        }catch (Exception exception){
            log.error("修改开票信息失败：",exception);
            ControllerHelper.sendErrorMsgJson(response,"修改开票信息失败：",exception);
        }
    }
    /**
     * 开票管理-新增/更新地址
     *
     * @param request 请求
     * @param response 响应
     * @param addressDTO 地址DTO
     * @author zhangjie
     * @date 2022/6/8
     * @return void
     */
    @ControllerLog(value = "开票管理-新增/更新地址")
    @PostMapping(value = "/operator-address")
    public void addAddress(HttpServletRequest request, HttpServletResponse response, AddressDTO addressDTO) throws IOException {
        try {
            ticketAddressService.saveOrUpdate(request,addressDTO);
            ControllerHelper.successJson(response);
        }catch (Exception exception){
            log.error("新增/更新地址失败：",exception);
            ControllerHelper.sendErrorMsgJson(response,"操作失败：",exception);
        }
    }
    /**
     * 开票管理-删除地址
     *
     * @param request 请求
     * @param response 响应
     * @param id 地址id
     * @author zhangjie
     * @date 2022/6/8
     * @return void
     */
    @ControllerLog(value = "开票管理-删除地址")
    @PostMapping(value = "/del-address")
    public void delAddress(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        try {
            ticketAddressService.removeById(id);
            ControllerHelper.successJson(response);
        }catch (Exception exception){
            log.error("删除地址失败：",exception);
            ControllerHelper.sendErrorMsgJson(response,"删除地址失败：",exception);
        }
    }
    @ControllerLog(value = "开票管理-获取地址列表")
    @PostMapping(value = "/address-list")
    public void addressList(HttpServletRequest request, HttpServletResponse response, DataTableParam dataTableParam) throws IOException {
        try {
            PageDT<TicketAddressVo> page = ticketAddressService.query(dataTableParam,request);
            ControllerHelper.putData(response,page);
        }catch (Exception ex){
            log.error("获取地址列表失败：",ex);
            ControllerHelper.putFailData(response,ex);
        }
    }
}
