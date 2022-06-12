package com.jw.elephant.capcplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
/**
 * 服务详情-结果类型枚举
 * @author zhangjie
 */
@Getter
public enum ServerDetailSupplierTypeEnum {
    /**
     * 结果类型枚举
     */
    OTHERS(0,"非三网"),
    CMCC(1,"中国移动"),
    CUCC(2,"中国联通"),
    CTCC(3,"中国电信"),
    ALL(4,"全网");
    @EnumValue
    private final Integer code;
    private final String codeStr;

    ServerDetailSupplierTypeEnum(Integer code, String codeStr) {
        this.code = code;
        this.codeStr = codeStr;
    }

    @Override
    public String toString() {
        return this.codeStr;
    }
}
