package org.epoch.core.exception;


import org.epoch.starter.core.constants.BaseConstants;

/**
 * 乐观锁更新异常
 *
 * @author Marshal
 */
public class OptimisticLockException extends RuntimeException {
    private static final long serialVersionUID = -4289111887481382553L;

    public OptimisticLockException() {
        super(BaseConstants.ErrorCode.OPTIMISTIC_LOCK);
    }

}
