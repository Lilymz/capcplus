package com.jw.elephant.capcplus.filter;

import com.jw.elephant.capcplus.constant.TextConstant;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 登录过滤器（没有登录的情况下不能去带有capcplus路由的地址）
 * @author zhangjie
 */
@WebFilter(urlPatterns = "/capcplus/*",filterName = "loginFilter")
@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        log.info("登录获取中.....");
        filterChain.doFilter(request,response);
    }
}
