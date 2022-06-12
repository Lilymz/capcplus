package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 服务-服务状态枚举
 * @author zhangjie
 */

@Getter
public enum ServerStatusEnum {
    /**
     * 服务状态枚举
     */
    WAIT(0,"待开启"),
    OPENED(1,"已开启"),
    RUNNING(2,"服务运行中"),
    PAUSE(3,"服务已暂停");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    ServerStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
