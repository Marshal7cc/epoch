package com.marshal.epoch.security.exception;

/**
 * @auth: Marshal
 * @date: 2020/1/28
 * @desc: 验证码认证失败异常
 */
public class ValidateCodeException extends Exception {
    public ValidateCodeException(String message) {
        super(message);
    }
}
