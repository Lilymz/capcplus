package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 开票信息-开票审核枚举
 * @author zhangjie
 */

@Getter
public enum TicketInfoStatusEnum {
    /**
     * 开票审核枚举
     */
    WAIT(1,"待审核"),
    SUCCESS(2,"审核通过"),
    FAIL(3,"审核驳回"),
    CANCEL(4,"撤销");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    TicketInfoStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
