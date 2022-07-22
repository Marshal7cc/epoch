package org.epoch.core.exception;

/**
 * <p>
 * 日期转换异常
 * </p>
 *
 * @author Marshal
 */
public class JsonProcessingException extends BaseException {

    private static final long serialVersionUID = -847651565156777077L;

    public JsonProcessingException() {
    }

    public JsonProcessingException(String message) {
        super(message);
    }

    public JsonProcessingException(String message, Object... parameters) {
        super(message, parameters);
    }

    public JsonProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
