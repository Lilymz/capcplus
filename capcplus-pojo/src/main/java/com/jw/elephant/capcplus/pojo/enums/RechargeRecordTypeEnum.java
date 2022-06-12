package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 充值记录-充值类型枚举
 * @author zhangjie
 */

@Getter
public enum RechargeRecordTypeEnum {
    /**
     * 充值类型枚举
     */
    ALL(0,"普通用户"),
    WAIT(1,"普通用户"),
    SUCCESS(2,"普通用户"),
    FAIL(3,"超级管理员");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    RechargeRecordTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
