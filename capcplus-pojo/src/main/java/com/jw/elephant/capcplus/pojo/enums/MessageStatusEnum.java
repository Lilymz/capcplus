package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 消息-消息状态枚举
 * @author zhangjie
 */
@Getter
public enum MessageStatusEnum {
    /**
     * 消息枚举
     */
    UNREAD(0,"未读"),
    READ(1,"已读");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    MessageStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
