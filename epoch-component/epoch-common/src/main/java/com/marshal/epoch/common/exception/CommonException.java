package com.marshal.epoch.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @auth: Marshal
 * @date: 2020/5/12
 * @desc: 通用标准异常类
 */
public abstract class CommonException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 参数=>用于传递一些信息（如果有必要的话）
     */
    private Object parameter;

    public CommonException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public CommonException(Integer code, String message) {
        this(code, message, null);
    }

    public CommonException(Integer code, String message, Object parameter) {
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
