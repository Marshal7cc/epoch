package org.epoch.core.exception;

/**
 * <p>
 * 日期转换异常
 * </p>
 *
 * @author Marshal
 */
public class JsonConvertException extends BaseException {

    private static final long serialVersionUID = -847651565156777077L;

    public JsonConvertException() {
    }

    public JsonConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
