package com.jw.elephant.capcplus.annotation;

import java.lang.annotation.*;

/**
 * 自定义web层日志记录注解
 * @author zhangjie
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
    String value() default "";
}
