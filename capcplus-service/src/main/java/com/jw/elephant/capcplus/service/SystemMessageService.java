package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.message.MessageSearchDTO;
import com.jw.elephant.capcplus.pojo.Message;

import javax.servlet.http.HttpServletRequest;

public interface SystemMessageService extends IService<Message> {
    /**
     *  获取消息数据
     *
     * @param dataTableParam 分页参数
     * @param messageSearchDTO 搜索参数
     * @author zhangjie
     * @date 2022/6/10
     * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.Server>
     */
    PageDT<Message> query(DataTableParam dataTableParam, MessageSearchDTO messageSearchDTO);

    Long countUnread(HttpServletRequest request);
}
