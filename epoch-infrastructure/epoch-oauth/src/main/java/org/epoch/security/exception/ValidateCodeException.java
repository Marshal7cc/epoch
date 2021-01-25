package org.epoch.security.exception;

/**
 * 验证码认证失败异常
 *
 * @author Marshal
 * @date 2020/1/28
 *
 */
public class ValidateCodeException extends Exception {
    public ValidateCodeException(String message) {
        super(message);
    }
}
