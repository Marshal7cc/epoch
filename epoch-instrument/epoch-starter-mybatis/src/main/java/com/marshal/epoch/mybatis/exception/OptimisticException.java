package com.marshal.epoch.mybatis.exception;

import com.marshal.epoch.core.base.BaseConstants;
import com.marshal.epoch.core.exception.CommonException;

/**
 * 乐观锁异常
 *
 * @author Marshal Yuan
 * @date 2020/5/30
 */
public class OptimisticException extends CommonException implements BaseConstants {
    public OptimisticException() {
        super(ResponseMessage.OPTIMISTIC_LOCK);
    }
}
