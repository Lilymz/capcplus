package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 统一认证-审核状态枚举
 * @author zhangjie
 */
@Getter
public enum CverificationAppInfoStatusEnum {
    /**
     * 审核状态枚举
     */
    UN_SUBMIT(0,"未提交"),
    WAIT(1,"待审核"),
    SUCCESS(2,"审核通过"),
    REFUSE(3,"审核拒绝");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    CverificationAppInfoStatusEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
