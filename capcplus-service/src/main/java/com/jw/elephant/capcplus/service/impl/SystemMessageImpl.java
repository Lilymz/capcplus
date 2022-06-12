package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.message.MessageSearchDTO;
import com.jw.elephant.capcplus.mapper.MessageMapper;
import com.jw.elephant.capcplus.pojo.Message;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.MessageStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.MessageTypeEnum;
import com.jw.elephant.capcplus.service.SystemMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 消息中心 service
 * @author zhangjie
 */
@Service
@Slf4j
public class SystemMessageImpl extends ServiceImpl<MessageMapper, Message> implements SystemMessageService {
    /**
     *  获取消息数据
     *
     * @param dataTableParam 分页参数
     * @param messageSearchDTO 搜索参数
     * @author zhangjie
     * @date 2022/6/10
     * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.Server>
     */
    @Override
    public PageDT<Message> query(DataTableParam dataTableParam, MessageSearchDTO messageSearchDTO) {
        Page page = DataTableParam.genPageParam(dataTableParam);
        if (Objects.nonNull(messageSearchDTO)){
            LambdaQueryWrapper<Message> messageWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(messageSearchDTO.getType())){
                messageWrapper.eq(Message::getType, MessageTypeEnum.valueOf(messageSearchDTO.getType()));
            }
            Page basePage = page(page, messageWrapper);
            PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
            return pageDT;
        }
        Page basePage = page(page);
        PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
        return pageDT;
    }

    /**
     * 统计未读数
     * @param request 请求
     * @return
     */
    @Override
    public Long countUnread(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(TextConstant.USER);
        LambdaQueryWrapper<Message> countWrapper = new LambdaQueryWrapper<>();
        countWrapper.select(Message::getId).eq(Message::getUserId,user.getId())
                .eq(Message::getStatus, MessageStatusEnum.UNREAD);
        Long count = list(countWrapper).stream().count();
        return count;
    }
}
