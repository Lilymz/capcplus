package com.jw.elephant.capcplus.controller.capcplus.message;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.message.MessageSearchDTO;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.pojo.Message;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.MessageStatusEnum;
import com.jw.elephant.capcplus.service.SystemMessageService;
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
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 消息中心模块
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping("/capcplus/message")
public class AdMessageController {
    @Resource
    private SystemMessageService service;
    @Resource
    private UserService userService;
    /****************************************************  go *********************************************/
    /**
     *  消息中心-去消息列表页
     * @author zhangjie
     * @date 2022/6/2
     * @return java.lang.String
     */
    @GetMapping("/go/list")
    public String goList(){
        return "/view/capcplus/message/message-list";
    }
    /****************************************************  service *********************************************/
    /**
     *  消息中心-获取消息列表
     *
     * @param request 请求
     * @param response 响应
     * @param dataTableParam 分页参数
     * @param messageSearchDTO 搜索参数DTO
     * @author zhangjie
     */
    @ControllerLog("消息中心-获取消息列表")
    @GetMapping("/message-list")
    public void list(HttpServletRequest request, HttpServletResponse response,
                     DataTableParam dataTableParam, MessageSearchDTO messageSearchDTO) throws IOException {
        try {
            PageDT<Message> page = service.query(dataTableParam, messageSearchDTO);
            ControllerHelper.putData(response,page);
        }catch (Exception ex){
            log.error("获取消息列表失败：",ex);
            ControllerHelper.putFailData(response,ex);
        }
    }
    /**
     *  消息中心-获取消息细节
     *
     * @param request 请求
     * @param response 响应
     * @param dataTableParam 分页参数
     * @param messageSearchDTO 搜索参数DTO
     * @author zhangjie
     */
    @ControllerLog("消息中心-获取消息细节")
    @PostMapping("/message-content")
    public void messageContent(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        try {
            Message message = service.getById(id);
            User user = userService.getById(message.getUserId());
            JSONObject result =new JSONObject();
            result.put("code",0000);
            result.put("message",message);
            result.put("user",user);
            ControllerHelper.successJson(response,result);
        }catch (Exception ex){
            log.error("获取消息细节失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"获取消息细节失败：",ex);
        }
    }
    /**
     *  消息中心-获取消息细节
     *
     * @param request 请求
     * @param response 响应
     * @param id 消息ids
     * @author zhangjie
     */
    @ControllerLog("消息中心-批量删除")
    @PostMapping("/batch-del")
    public void batchDel(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        try {
            Set<String> ids = Arrays.stream(id.split(",")).collect(Collectors.toSet());
            service.removeBatchByIds(ids);
            ControllerHelper.successJson(response);
        }catch (Exception ex){
            log.error("批量删除失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"批量删除失败：",ex);
        }
    }
    /**
     *  消息中心-获取消息细节
     *
     * @param request 请求
     * @param response 响应
     * @param id 消息ids
     * @author zhangjie
     */
    @ControllerLog("消息中心-批量已读")
    @PostMapping("/batch-read")
    public void batchRead(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        try {
            Set<String> ids = Arrays.stream(id.split(",")).collect(Collectors.toSet());
            LambdaUpdateWrapper<Message> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(Message::getStatus, MessageStatusEnum.READ)
                        .in(Message::getId,ids);
            service.update(updateWrapper);
            ControllerHelper.successJson(response);
        }catch (Exception ex){
            log.error("批量已读失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"批量已读失败：",ex);
        }
    }
    /**
     *  消息中心-获取消息数量
     *
     * @param request 请求
     * @param response 响应
     * @author zhangjie
     */
    @ControllerLog("消息中心-获取消息数量")
    @GetMapping("/message-unread-count")
    public void batchRead(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Long count = service.countUnread(request);
            JSONObject result = new JSONObject();
            result.put("count",count);
            result.put("code",0000);
            ControllerHelper.successJson(response,result);
        }catch (Exception ex){
            log.error("获取消息数量失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"获取消息数量失败：",ex);
        }
    }
}
