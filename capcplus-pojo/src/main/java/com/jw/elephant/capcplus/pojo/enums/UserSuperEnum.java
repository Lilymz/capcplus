package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 用户 -用户超级管理枚举
 * @author zhangjie
 */

@Getter
public enum UserSuperEnum {
    /**
     * 发票状态枚举
     */
    NO(0,"普通用户"),
    YES(1,"超级用户");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    UserSuperEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
