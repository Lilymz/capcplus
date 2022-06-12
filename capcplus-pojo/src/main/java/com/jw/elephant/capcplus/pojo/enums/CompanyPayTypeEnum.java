package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
/**
 * 企业-付费类型枚举
 * @author zhangjie
 */
@Getter
public enum CompanyPayTypeEnum {
    /**
     * 付费类型枚举
     */
    ADVANCE(0,"预付费"),
    LATER(1,"后付费");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    CompanyPayTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
