package com.jw.elephant.capcplus.service;

import javax.servlet.http.HttpServletRequest;

public interface MessageMailService {
    /**
     * 发送邮件验证码
     *
     * @param toUser
     * @param subject
     * @param code
     * @author zhangjie
     * @date 2022/5/27
     */
    void simpleMail(String toUser,String subject,String code);
    /**
     *  发送余额预警邮件
     *
     * @param toUser 用户邮箱
     * @param subject 主题
     * @param user 对应用户
     * @author zhangjie
     * @date 2022/5/30
     */
    void tipBalanceMail(HttpServletRequest request,String toUser, String subject, String user);
}
