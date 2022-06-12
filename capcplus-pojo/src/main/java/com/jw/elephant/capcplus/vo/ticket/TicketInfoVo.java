package com.jw.elephant.capcplus.vo.ticket;

import com.jw.elephant.capcplus.pojo.TicketInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发票记录vo
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInfoVo extends TicketInfo {

    private String creditCode;

    private String ticketMan;

    public TicketInfoVo(TicketInfo ticketInfo) {
        super(ticketInfo.getId(),ticketInfo.getUserId(),ticketInfo.getTicketId()
                ,ticketInfo.getTicketType(),ticketInfo.getSealType(),ticketInfo.getServiceIds()
                ,ticketInfo.getBillIds(),ticketInfo.getTicketAddressId(),ticketInfo.getIsPaper()
                ,ticketInfo.getStatus(),ticketInfo.getTicketProject(),ticketInfo.getTicketBalance()
                ,ticketInfo.getApplyTime(),ticketInfo.getRemark(), ticketInfo.getCreateTime()
                ,ticketInfo.getUpdateTime(),ticketInfo.getIsDel());
    }
}
