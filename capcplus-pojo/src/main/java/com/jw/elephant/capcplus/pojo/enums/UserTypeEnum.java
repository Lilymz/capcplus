package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 *  用户-用户类型枚举
 * @author zhangjie
 */

@Getter
public enum UserTypeEnum {
    /**
     * 用户类型枚举
     */
    ORDINARY(0,"普通用户"),
    ADMIN(1,"超级管理员");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    UserTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
