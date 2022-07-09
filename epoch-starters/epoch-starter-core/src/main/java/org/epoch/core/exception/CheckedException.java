package org.epoch.core.exception;

import org.springframework.http.HttpStatus;

/**
 * 受检异常
 *
 * @author Marshal
 * @date 2020/5/30
 */
public class CheckedException extends Exception {

    private static final long serialVersionUID = 1L;
    private final transient Object[] parameters;
    private String code;


    public CheckedException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR.toString(), message);
    }

    public CheckedException(String code, String message) {
        this(code, message, null);
    }

    public CheckedException(String code, String message, Object... parameters) {
        super(message);
        this.code = code;
        this.parameters = parameters;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return this.getMessage();
    }

    public Object[] getParameter() {
        return parameters;
    }
}
