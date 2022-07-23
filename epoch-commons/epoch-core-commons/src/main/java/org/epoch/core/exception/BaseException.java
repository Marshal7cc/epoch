package org.epoch.core.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Base parent exception.
 *
 * @author Marshal
 * @date 2020/5/12
 */
@Slf4j
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * the detail code.
     * A common use case here is for response code.
     */
    @Getter
    private String code;
    /**
     * Message format parameters.
     */
    @Getter
    private transient Object[] parameters;

    public BaseException() {
    }

    public BaseException(String message, Object... parameters) {
        super(message);
        this.parameters = parameters;
    }

    public BaseException(String message, Throwable cause, Object... parameters) {
        super(message, cause);
        this.parameters = parameters;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.parameters = new Object[]{};
    }


    public BaseException(Throwable cause, Object... parameters) {
        super(cause);
        this.parameters = parameters;
    }

    public BaseException withCode(String code) {
        this.code = code;
        return this;
    }


    public String getTrace() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = null;
        try {
            ps = new PrintStream(baos, false, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error("Error get trace, unsupported encoding.", e);
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        this.printStackTrace(ps);
        ps.flush();
        return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put("code", code);
        map.put("message", super.getMessage());
        return map;
    }
}
