package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 统一认证-app状态枚举
 * @author zhangjie
 */
@Getter
public enum CverificationAppInfoAppStatusEnum {
    /**
     * 审核状态枚举
     */
    OPEN(0,"开启"),
    CLOSE(1,"关闭");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    CverificationAppInfoAppStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
