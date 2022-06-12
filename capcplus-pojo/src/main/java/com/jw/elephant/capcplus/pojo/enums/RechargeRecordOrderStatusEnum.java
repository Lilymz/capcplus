package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 充值记录-充值订单状态枚举
 * @author zhangjie
 */

@Getter
public enum RechargeRecordOrderStatusEnum {
    /**
     * 充值订单状态枚举
     */
    ALL(0,"全部"),
    DOING(1,"交易中"),
    FAIL(2,"交易失败"),
    SUCCESS(3,"交易成功"),
    CANCEL(4,"交易取消"),
    WAIT_EXAMINE(5,"待财务审核"),
    EXAMINE_FAIL(6,"财务驳回");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    RechargeRecordOrderStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
