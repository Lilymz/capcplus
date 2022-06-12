package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.dto.ticket.TicketDTO;
import com.jw.elephant.capcplus.pojo.Ticket;

import javax.servlet.http.HttpServletRequest;

public interface TicketService extends IService<Ticket> {
    /**
     *  修改开票信息
     * @param request 请求
     * @param ticketDTO 开票DTO
     * @author zhangjie
     * @date 2022/6/8
     * @return void
     */
    void saveOrUpdate(HttpServletRequest request, TicketDTO ticketDTO);
}
