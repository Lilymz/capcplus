package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 权限-权限状态枚举
 * @author zhangjie
 */

@Getter
public enum PermissionEnum {
    /**
     * 权限状态枚举
     */
    FUNCTION(0L,"普通用户"),
    OPERATOR(1L,"超级管理员");
    @EnumValue
    private final Long code;
    private final String codeStr;

    PermissionEnum(Long code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
