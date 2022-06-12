package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
/**
 * 企业-企业状态枚举
 * @author zhangjie
 */

@Getter
public enum CompanyStatusEnum {
    /**
     * 企业状态枚举
     */
    NORMAL(0,"正常"),
    BALANCE_EXCEPTION(1,"余额异常"),
    SYS_EXCEPTION(2,"系统异常");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    CompanyStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
