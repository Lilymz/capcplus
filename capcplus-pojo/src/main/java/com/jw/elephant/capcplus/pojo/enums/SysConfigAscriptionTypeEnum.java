package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 系统配置-归属类型枚举
 * @author zhangjie
 */
@Getter
public enum SysConfigAscriptionTypeEnum {
    /**
     * 归属类型枚举
     */
    SYSTEM(0,"系统"),
    USER(1,"用户"),
    ADMIN(2,"管理员");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    SysConfigAscriptionTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
