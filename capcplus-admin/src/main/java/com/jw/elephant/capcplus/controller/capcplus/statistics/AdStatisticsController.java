package com.jw.elephant.capcplus.controller.capcplus.statistics;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jw.elephant.capcplus.EchartsData;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.statistics.StatisticsSearchDTO;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.pojo.Server;
import com.jw.elephant.capcplus.pojo.enums.ServerTypeEnum;
import com.jw.elephant.capcplus.service.ServerService;
import com.jw.elephant.capcplus.service.StatisticsService;
import com.jw.elephant.capcplus.vo.statistics.StatisticsVo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据统计
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping("/capcplus/statistics")
public class AdStatisticsController {
    @Resource
    private ServerService serverService;
    @Resource
    private StatisticsService service;
    /**************************************************** go **********************************************/
    /**
     * 数据统计-去数据统计列表
     *
     * @author zhangjie
     * @date 2022/6/1
     * @return java.lang.String
     */
    @GetMapping("/go/list")
    public String goList(HttpServletRequest request, Model model){
        //获取当前所有服务类型
        Set<ServerTypeEnum> serverTypeEnums = serverService.list(new LambdaQueryWrapper<Server>().select(Server::getType))
                .stream().map(Server::getType).collect(Collectors.toSet());
        model.addAttribute("serverTypes",serverTypeEnums);
        return "/view/capcplus/statistics/statistics-list";
    }
    /************************************************** service ************************************************/
    /**
     *  数据统计-获取数据统计列表
     *
     * @param request 请求
     * @param response 响应
     * @param dataTableParam 分页参数
     * @param searchDTO 查询参数
     * @author zhangjie
     */
    @ControllerLog("数据统计-获取数据统计列表")
    @GetMapping("/statistics-list")
    public void list(HttpServletRequest request, HttpServletResponse response,
                     DataTableParam dataTableParam, StatisticsSearchDTO searchDTO) throws IOException {
        try {
            PageDT<StatisticsVo> page = service.query(dataTableParam, searchDTO);
            ControllerHelper.putData(response,page);
        }catch (Exception ex){
            log.error("获取数据统计列表失败：",ex);
            ControllerHelper.putFailData(response,ex);
        }
    }
    /**
     * 查询服务名称
     *
     * @param request 请求
     * @param response 响应
     * @param typeEnum 服务类型
     * @author zhangjie
     * @date 2022/6/8
     * @return void
     */
    @ControllerLog("数据统计-查询服务名称")
    @PostMapping("/get-server-name")
    public void getServerName(HttpServletRequest request, HttpServletResponse response,String typeEnum) throws IOException {
        try {
            List<Server> list = new ArrayList<>(8);
            if (StringUtils.isNotBlank(typeEnum)){
                serverService.list(new LambdaQueryWrapper<Server>().select(Server::getName)
                        .eq(Server::getType, ServerTypeEnum.valueOf(typeEnum)));
            }
            Set<String> serverNames = list.stream().map(Server::getName).collect(Collectors.toSet());
            JSONObject result = new JSONObject();
            result.put("serverNames",serverNames);
            result.put("code",0000);
            ControllerHelper.successJson(response,result);
        }catch (Exception ex){
            log.error("查询服务名称失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"查询服务名称失败：",ex);
        }
    }
    /**
     *  数据统计-获取成功率数据
     *
     * @param request 请求
     * @param response 响应
     * @param dataTableParam 分页参数
     * @param searchDTO 查询参数
     * @author zhangjie
     */
    @ControllerLog("数据统计-获取成功率数据")
    @GetMapping("/success-statistics")
    public void successStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            JSONObject result = new JSONObject();
            List<EchartsData> statistics = service.successStatistics(request);
            result.put("code",0000);
            result.put("statistics",statistics);
            ControllerHelper.successJson(response,result);
        }catch (Exception ex){
            log.error("获取成功率数据失败：",ex);
            ControllerHelper.sendErrorMsgJson(response,"获取成功率数据失败：",ex);
        }
    }
}
