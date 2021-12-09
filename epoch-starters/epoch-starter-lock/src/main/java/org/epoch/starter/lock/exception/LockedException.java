package org.epoch.starter.lock.exception;

import org.epoch.core.exception.CommonException;

/**
 * @author Marshal
 * @since 2021/12/9
 */
public class LockedException extends CommonException {
    public LockedException(String code, Object... parameters) {
        super(code, parameters);
    }
}
