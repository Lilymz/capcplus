package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 开票信息-开票信息类型枚举
 * @author zhangjie
 */

@Getter
public enum TicketInfoTypeEnum {
    /**
     * 开票信息类型枚举
     */
    VAT_INVOICE(1,"增值税普通发票"),
    SPECIAL_INVOICE(2,"增值税专用发票");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    TicketInfoTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
