package org.epoch.lock.exception;

import org.epoch.core.exception.BaseException;

/**
 * @author Marshal
 * @since 2021/12/9
 */
public class LockedException extends BaseException {
    public LockedException(String code, Object... parameters) {
        super(code, parameters);
    }
}
