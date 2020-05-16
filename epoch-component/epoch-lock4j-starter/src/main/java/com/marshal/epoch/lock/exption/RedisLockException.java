package com.marshal.epoch.lock.exption;

/**
 * @author Gu.Zhende
 * @description
 * @date 2018年10月14日下午1:22:40
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
