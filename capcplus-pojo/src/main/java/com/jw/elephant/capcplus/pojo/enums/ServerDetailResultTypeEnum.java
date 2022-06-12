package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 服务详情-结果类型枚举
 * @author zhangjie
 */

@Getter
public enum ServerDetailResultTypeEnum {
    /**
     * 结果类型枚举
     */
    CODE(0,"返回码"),
    CODE_DESCRIPTION(1,"返回码描述");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    ServerDetailResultTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
