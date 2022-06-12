package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.dto.UserLoginDTO;
import com.jw.elephant.capcplus.dto.UserRegisterDTO;
import com.jw.elephant.capcplus.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * userService
 *
 * @author zhangjie
 */
public interface UserService extends IService<User> {
    /**
     *  登录接口
     *
     * @param request 当前请求
     * @param response 当前响应
     * @param userLoginDTO 登录DTO
     * @author zhangjie
     * @date 2022/5/25
     */
    void login(HttpServletRequest request, HttpServletResponse response, UserLoginDTO userLoginDTO);
    /**
     *  注册接口
     *
     * @param request 当前请求
     * @param response 当前响应
     * @param userLoginDTO 登录DTO
     * @author zhangjie
     * @date 2022/5/26
     */
    void register(HttpServletRequest request, HttpServletResponse response, UserRegisterDTO userLoginDTO);
    /**
     *  账号是否存在
     *
     * @param request 请求
     * @param response 响应
     * @param account 当前账号
     * @author zhangjie
     * @date 2022/5/26
     */
    boolean existAccount(HttpServletRequest request, HttpServletResponse response, String account);
    /**
     * 发送邮件
     *
     * @param request 请求
     * @param response 响应
     * @param account 当前账号
     * @param email 当前邮箱
     * @author zhangjie
     * @date 2022/5/26
     */
    Long sendEmail(HttpServletRequest request, HttpServletResponse response, String account,String email);
    /**
     *  修改密码
     *
     * @param request 请求
     * @param response 响应
     * @param id 当前用户id
     * @param password 新密码
     * @param againPassword 确认密码
     * @author zhangjie
     */
    boolean changePassWord(HttpServletRequest request, HttpServletResponse response, Long id,String password,String againPassword);
}
