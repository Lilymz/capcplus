package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.ticket.TicketApplyDTO;
import com.jw.elephant.capcplus.dto.ticket.TicketInfoSearchDTO;
import com.jw.elephant.capcplus.pojo.TicketInfo;
import com.jw.elephant.capcplus.vo.ticket.TicketInfoVo;

import javax.servlet.http.HttpServletRequest;

public interface TicketInfoService extends IService<TicketInfo> {
    /**
     * 申请发票(审核通过后,已申请过的不可在申请)
     *
     * @param request 请求
     * @param ticketApplyDTO 发票申请DTO
     * @author zhangjie
     * @date 2022/6/9
     * @return void
     */
    void save(HttpServletRequest request, TicketApplyDTO ticketApplyDTO);
    /**
     * 获取发票记录
     * @param dataTableParam 分页参数
     * @param request 请求
     * @param ticketInfoSearchDTO  条件DTO
     * @return
     */
    PageDT<TicketInfoVo> query(DataTableParam dataTableParam, HttpServletRequest request, TicketInfoSearchDTO ticketInfoSearchDTO);
}
