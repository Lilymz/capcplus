package com.jw.elephant.capcplus.service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface KaptchaService {
    /**
     *  获取验证码服务
     * @param request
     * @return
     * @throws IOException
     */
    ByteArrayOutputStream getCode(HttpServletRequest request) throws IOException;
}
