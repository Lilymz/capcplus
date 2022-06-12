package com.jw.elephant.capcplus.dto.recharge;

import com.jw.elephant.capcplus.constant.DateConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 支付DTO
 * @author zhangjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeRecordPayDTO {
    /**
     * 处理逻辑：1：支付，2取消支付
     */
    private Integer type;

    /**
     * 提交时间
     */
    @DateTimeFormat(pattern = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date submitTime;

    /**
     * 当前充值记录id
     */
    private Long id;
}
