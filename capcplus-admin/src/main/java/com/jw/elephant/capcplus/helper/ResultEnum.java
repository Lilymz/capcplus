package com.jw.elephant.capcplus.helper;

/**
 * 响应枚举（该枚举列举了常用的响应状态和响应信息）
 * @author zhangjie
 */

public enum ResultEnum {
    /**
     * 枚举常量
     */
    SUCCESS("0000","一切正常"),
    REGISTER_FAIL("A001","用户注册错误"),
    DISAGREE_PROTOCOL("A002","未同意协议"),
    USERNAME_VERIFY_ERROR("A003","用户名校验失败"),
    USERNAME_EXIST("A004","用户名已存在"),
    USERNAME_ILLEGAL("A005","用户名包含非法字符"),
    PASSWORD_ERROR("A006","用户密码错误"),
    PASSWORD_LENGTH_NOT_ENOUGH("A007","密码长度不够"),
    PASSWORD_WEAK("A008","密码强度太低"),
    CODE_ERROR("A009","验证码错误"),
    SMS_CODE_ERROR("A010","短信验证码错误"),
    LOGIN_SUCCESS("A011","登录成功!");
    /**
     * 返回码
     */
    private String code;
    /**
     * 信息
     */
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static String getMsg(ResultEnum resultEnum){
        for (ResultEnum result : ResultEnum.values()) {
            if (result.code.equals(resultEnum.code)){
                return result.msg;
            }
        }
        return "";
    }
    public static String getCode(ResultEnum resultEnum){
        for (ResultEnum result : ResultEnum.values()) {
            if (result.code.equals(resultEnum.code)){
                return result.code;
            }
        }
        return "";
    }
}
