package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 系统配置-系统配置类型枚举
 * @author zhangjie
 */

@Getter
public enum SysConfigTypeEnum {
    /**
     * 系统配置类型枚举
     */
    INDEX(1,"首页"),
    APP_SCENE(2,"应用场景"),
    PRODUCT_SERVER(3,"产品服务"),
    INTERFACE_API(4,"接口api"),
    WHITE_LIST(5,"白名单");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    SysConfigTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
