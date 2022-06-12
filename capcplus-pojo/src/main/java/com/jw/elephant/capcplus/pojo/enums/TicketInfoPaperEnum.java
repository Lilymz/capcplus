package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 开票信息-开票纸质枚举
 * @author zhangjie
 */

@Getter
public enum TicketInfoPaperEnum {
    /**
     * 开票纸质枚举
     */
    NO(0,"不默认"),
    YES(1,"默认");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    TicketInfoPaperEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
