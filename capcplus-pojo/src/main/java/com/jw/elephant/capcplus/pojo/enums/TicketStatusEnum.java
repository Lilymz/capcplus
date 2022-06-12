package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 发票-发票状态枚举
 * @author zhangjie
 */

@Getter
public enum TicketStatusEnum {
    /**
     * 发票状态枚举
     */
    WAIT(1,"待审核"),
    SUCCESS(2,"审核通过"),
    FAIL(3,"驳回");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    TicketStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
