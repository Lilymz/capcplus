package com.jw.elephant.capcplus.dto.recharge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  充值记录搜索条件
 *
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeRecordSearchDTO {
    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 支付流水号
     */
    private String paySerialNumber;

    /**
     * 支付状态
     */
    private Integer payStatus;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 充值类型
     */
    private Integer type;
}
