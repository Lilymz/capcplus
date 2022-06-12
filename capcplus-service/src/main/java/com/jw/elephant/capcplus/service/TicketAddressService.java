package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.address.AddressDTO;
import com.jw.elephant.capcplus.pojo.TicketAddress;
import com.jw.elephant.capcplus.vo.ticket.TicketAddressVo;

import javax.servlet.http.HttpServletRequest;

public interface TicketAddressService extends IService<TicketAddress> {
    /**
     *  地址新增或更新
     * @param request 请求
     * @param addressDTO 响应
     * @author zhangjie
     * @return void
     */
    void saveOrUpdate(HttpServletRequest request, AddressDTO addressDTO);
    /**
     * 获取地址列表数据
     *
     * @param dataTableParam 分页参数
     * @author zhangjie
     * @date 2022/6/9
     * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.TicketAddress>
     */
    PageDT<TicketAddressVo> query(DataTableParam dataTableParam,HttpServletRequest request);
}
