package com.jw.elephant.capcplus.controller.capcplus.detail;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.server.detail.ServerDetailSearchDTO;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.pojo.Server;
import com.jw.elephant.capcplus.pojo.enums.ServerTypeEnum;
import com.jw.elephant.capcplus.service.ServerDetailService;
import com.jw.elephant.capcplus.service.ServerService;
import com.jw.elephant.capcplus.vo.server.detail.ServerDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据详情
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping("/capcplus/detail")
public class AdServerDetailController {
    @Resource
    private ServerDetailService service;
    @Resource
    private ServerService serverService;
    /***************************************************** go  *************************************************/
    @GetMapping("/go/list")
    public String goList(Model model){
        //获取当前所有服务类型
        Set<ServerTypeEnum> serverTypeEnums = serverService.list(new LambdaQueryWrapper<Server>().select(Server::getType))
                .stream().map(Server::getType).collect(Collectors.toSet());
        model.addAttribute("serverTypes",serverTypeEnums);
        return "/view/capcplus/detail/data-detail";
    }
    /************************************************** service ************************************************/
    /**
     *  数据详情-获取数据详情列表
     *
     * @param request 请求
     * @param response 响应
     * @param dataTableParam 分页参数
     * @param searchDTO 查询参数
     * @author zhangjie
     */
    @ControllerLog("数据统计-获取数据统计列表")
    @GetMapping("/detail-list")
    public void list(HttpServletRequest request, HttpServletResponse response,
                     DataTableParam dataTableParam, ServerDetailSearchDTO searchDTO) throws IOException {
        try {
            PageDT<ServerDetailVo> page = service.query(dataTableParam, searchDTO);
            ControllerHelper.putData(response,page);
        }catch (Exception ex){
            log.error("获取数据统计列表失败：",ex);
            ControllerHelper.putFailData(response,ex);
        }
    }
}
