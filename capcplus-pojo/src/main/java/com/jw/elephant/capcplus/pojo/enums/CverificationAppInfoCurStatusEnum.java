package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 统一认证-当前状态枚举
 * @author zhangjie
 */
@Getter
public enum CverificationAppInfoCurStatusEnum {
    /**
     * 统一认证当前状态枚举
     */
    NORMAL(0,"正常"),
    INVALID(1,"无效");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    CverificationAppInfoCurStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
