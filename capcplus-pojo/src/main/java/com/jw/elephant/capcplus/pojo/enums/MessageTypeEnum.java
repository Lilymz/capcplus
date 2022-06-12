package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 消息-消息类型枚举
 * @author zhangjie
 */
@Getter
public enum MessageTypeEnum {
    /**
     * 消息枚举
     */
    SERVICE_MESSAGE(1,"服务消息"),
    SYSTEM_MESSAGE(2,"系统消息"),
    BALANCE_ALERT(3,"余额预警"),
    DATA_MODIFY(4,"资料修改");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    MessageTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
