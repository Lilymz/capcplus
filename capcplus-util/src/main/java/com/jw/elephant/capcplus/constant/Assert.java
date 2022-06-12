package com.jw.elephant.capcplus.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 该类是用于controller曾断言类
 * 不用在多些try catch
 */
public class Assert extends RuntimeException{
    /**
     * 该值通过全限定名计算出来的hash值
     */
    static final long serialVersionUID = 154971414L;
    /****************************************** 构造器  ************************************************/
    public Assert(){
        super();
    }
    public Assert(String msg){
        super(msg);
    }
    public Assert(String msg,Throwable throwable){
        super(msg,throwable);
    }
    /****************************************** method  ************************************************/
    /**
     * 真值断言 条件为真抛出异常
     *
     * @param condition 判断条件
     * @param msg 消息
     * @author zhangjie
     * @date 2022/5/20
     */
    public static void isTrue(boolean condition,String msg){
        if (condition){
            throw new Assert(msg);
        }
    }
    /**
     * 真值断言 条件为假抛出异常
     *
     * @param condition 判断条件
     * @param msg 消息
     * @author zhangjie
     * @date 2022/5/20
     */
    public static void notTrue(boolean condition,String msg){
        isTrue(!condition,msg);
    }
    /**
     * 对象null 贼抛出断言异常
     *
     * @param obj 当前判断对象
     * @param msg 消息
     * @author zhangjie
     * @date 2022/5/20
     */
    public static void isNull(Object obj,String msg){
        if (Objects.isNull(obj)){
            throw new Assert(msg);
        }
    }
    /**
     * 对象非null 贼抛出断言异常
     *
     * @param obj 当前判断对象
     * @param msg 消息
     * @author zhangjie
     * @date 2022/5/20
     */
    public static void nonNull(Object obj,String msg){
        if (Objects.nonNull(obj)){
            throw new Assert(msg);
        }
    }

    /**
     * 字符串空白 抛出异常
     * @param obj 对应字符串
     * @param msg 消息
     */
    public static void isBlank(String obj,String msg){
        if (StringUtils.isBlank(obj)){
            throw new Assert(msg);
        }
    }

    /**
     * 字符串非空白 抛出异常
     * @param obj 对应字符串
     * @param msg 消息
     */
    public static void isNotBlank(String obj,String msg){
        if (StringUtils.isNotBlank(obj)){
            throw new Assert(msg);
        }
    }
}
