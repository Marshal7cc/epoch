package com.marshal.epoch.lock.exption;

/**
 * @author Marshal
 **/
public class RedisLockException extends RuntimeException {

    private static final long serialVersionUID = -6982737421979121466L;

    public RedisLockException(String message) {
        super(message);
    }

    public RedisLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisLockException(Throwable cause) {
        super(cause);
    }
}
