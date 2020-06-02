package com.marshal.epoch.security.service;

import com.marshal.epoch.security.exception.ValidateCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Marshal
 * @date 2020/1/15
 */
public interface ValidateCodeService {

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ValidateCodeException 验证码异常
     */
    void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException;

    /**
     * 校验验证码
     *
     * @param key
     * @param value
     * @throws ValidateCodeException
     */
    void check(String key, String value) throws ValidateCodeException;

}
