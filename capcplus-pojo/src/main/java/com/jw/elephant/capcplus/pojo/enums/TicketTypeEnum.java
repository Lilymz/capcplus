package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 发票-发票类型枚举
 * @author zhangjie
 */

@Getter
public enum TicketTypeEnum {
    /**
     * 发票类型枚举
     */
    SMALL_SCALE(1,"小规模纳税人"),
    ORDINARY(2,"一般纳税人");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    TicketTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
