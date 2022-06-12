package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 充值记录-充值支付状态枚举
 * @author zhangjie
 */

@Getter
public enum RechargeRecordPayStatusEnum {
    /**
     * 充值支付状态枚举
     */
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    FAIL(2,"支付失败");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    RechargeRecordPayStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
