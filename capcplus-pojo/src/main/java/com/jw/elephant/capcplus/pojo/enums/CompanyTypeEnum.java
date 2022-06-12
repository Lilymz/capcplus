package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 企业-企业类型枚举
 * @author zhangjie
 */

@Getter
public enum CompanyTypeEnum {
    /**
     * 企业类型枚举
     */
    FIRM(0,"企业"),
    PUBLIC_INSTITUTION(1,"事业单位"),
    PEOPLE_NOT_PUBLIC_INSTITUTION(2,"民办非企业单位"),
    OWNER_FIRM(3,"个体工商户"),
    SOCIAL_GROUPS(4,"社会团体"),
    GOVERNMENT(5,"党政及国家机关");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    CompanyTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
