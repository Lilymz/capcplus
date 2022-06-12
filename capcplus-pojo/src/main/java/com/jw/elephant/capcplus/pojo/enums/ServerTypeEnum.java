package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 服务-服务类型枚举
 * @author zhangjie
 */
@Getter
public enum ServerTypeEnum {
    /**
     * 服务类型枚举
     */
    PHONE_INFO(1,"手机号信息类"),
    SMS(2,"短信类"),
    VOICE(3,"语音类"),
    CERTIFICATION(4,"统一认证");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    ServerTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
