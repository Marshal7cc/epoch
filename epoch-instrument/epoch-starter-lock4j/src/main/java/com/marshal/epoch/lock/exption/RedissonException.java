package com.marshal.epoch.lock.exption;

/**
 * @author Marshal
 **/
public class RedissonException extends RuntimeException {

    private static final long serialVersionUID = 3399210954142709500L;

    public RedissonException() {
        super();
    }

    public RedissonException(String message) {
        super(message);
    }

    public RedissonException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedissonException(Throwable cause) {
        super(cause);
    }
}
