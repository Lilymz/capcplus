package com.jw.elephant.capcplus.vo.ticket;

import com.jw.elephant.capcplus.pojo.TicketAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址VO
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketAddressVo extends TicketAddress{
    /**
     * 省份str
     */
    private String provinceStr;
    /**
     * 城市str
     */
    private String cityStr;
    /**
     * 区域str
     */
    private String areaStr;

    /**
     * 构造器
     * @param ticketAddress 地址构造器
     */
    public TicketAddressVo(TicketAddress ticketAddress) {
        super(ticketAddress.getId(),ticketAddress.getUserId(), ticketAddress.getName()
                ,ticketAddress.getProvinceId(),ticketAddress.getCityId(),ticketAddress.getAreaId()
                ,ticketAddress.getDetailAddress(),ticketAddress.getPostCode(),ticketAddress.getPhone()
                ,ticketAddress.getTelephone(),ticketAddress.getIsDefault(),ticketAddress.getCreateTime()
                ,ticketAddress.getUpdateTime(),ticketAddress.getIsDel());
    }
}
