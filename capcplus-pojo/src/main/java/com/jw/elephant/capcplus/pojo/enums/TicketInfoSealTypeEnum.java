package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 开票信息-开票盖章枚举
 * @author zhangjie
 */

@Getter
public enum TicketInfoSealTypeEnum {
    /**
     * 开票盖章枚举
     */
    FINANCE(0,"财务章"),
    CONTRACT(1,"合同章"),
    OFFICIAL(2,"公章");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    TicketInfoSealTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
