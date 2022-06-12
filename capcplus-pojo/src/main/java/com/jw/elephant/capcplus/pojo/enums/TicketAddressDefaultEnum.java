package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 发票地址-发票地址默认枚举
 * @author zhangjie
 */

@Getter
public enum TicketAddressDefaultEnum {
    /**
     * 发票地址默认枚举
     */
    NO(0,"不默认"),
    YES(1,"默认");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    TicketAddressDefaultEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
