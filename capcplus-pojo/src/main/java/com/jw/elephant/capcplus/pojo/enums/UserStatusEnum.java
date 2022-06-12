package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 用户-用户状态枚举
 * @author zhangjie
 */

@Getter
public enum UserStatusEnum {
    /**
     * 用户状态枚举
     */
    NORMAL(0,"正常"),
    DISABLED(1,"禁用");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    UserStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
