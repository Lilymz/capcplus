package com.jw.elephant.capcplus.dto.ticket;

import com.jw.elephant.capcplus.constant.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发票申请DTO
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketApplyDTO {
    private String ticketId;
    private String ticketType;
    private String sealType;
    private String serverIds;
    private String billIds;
    private String ticketAddressId;
    private String isPaper;
    private String ticketProject;
    private String ticketBalance;
    public static void check(TicketApplyDTO ticketApplyDTO){
        Assert.isBlank(ticketApplyDTO.getTicketId(),"未选择对应发票！");
        Assert.isBlank(ticketApplyDTO.getTicketType(),"未选择发票类别！");
        Assert.isBlank(ticketApplyDTO.getSealType(),"未选择盖章类型！");
        Assert.isBlank(ticketApplyDTO.getServerIds(),"未选择对应服务！");
        Assert.isBlank(ticketApplyDTO.getBillIds(),"未选择对应账单！");
        Assert.isBlank(ticketApplyDTO.getTicketAddressId(),"未选择收票地址！");
        Assert.isBlank(ticketApplyDTO.getIsPaper(),"未选择纸质对账单！");
        Assert.isBlank(ticketApplyDTO.getTicketProject(),"未选择发票项目！");
        Assert.isBlank(ticketApplyDTO.getTicketBalance(),"未选择发票金额！");
    }
}
