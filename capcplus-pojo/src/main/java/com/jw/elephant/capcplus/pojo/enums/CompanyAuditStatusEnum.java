package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 企业-企业审核状态枚举
 * @author zhangjie
 */

@Getter
public enum CompanyAuditStatusEnum {
    /**
     * 企业审核状态枚举
     */
    UN_SUBMIT(0,"未提交"),
    SUCCESS(1,"审核通过"),
    REFUSE(2,"审核拒绝"),
    WAIT(3,"待审核");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    CompanyAuditStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
