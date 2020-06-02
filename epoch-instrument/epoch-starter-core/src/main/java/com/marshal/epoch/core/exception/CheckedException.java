package com.marshal.epoch.core.exception;

import org.springframework.http.HttpStatus;

/**
 * 受检异常
 *
 * @author Marshal Yuan
 * @date 2020/5/30
 */
public class CheckedException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 参数=>用于传递一些信息（如果有必要的话）
     */
    private Object parameter;

    public CheckedException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public CheckedException(Integer code, String message) {
        this(code, message, null);
    }

    public CheckedException(Integer code, String message, Object parameter) {
        super(message);
        this.code = code;
        this.parameter = parameter;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return this.getMessage();
    }

    public Object getParameter() {
        return parameter;
    }
}
