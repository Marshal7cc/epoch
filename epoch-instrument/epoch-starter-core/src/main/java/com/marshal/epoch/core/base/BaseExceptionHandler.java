package com.marshal.epoch.core.base;


import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.core.exception.CommonException;
import com.marshal.epoch.core.rest.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 *
 * @author Marshal
 * @date 2018/10/26
 */
@ControllerAdvice
public class BaseExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    /**
     * 异常处理
     *
     * @param commonException
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity handleException(CommonException commonException) {
        if (logger.isDebugEnabled()) {
            logger.debug(commonException.getDescription());
        }
        return Response.fail(commonException.getCode(), commonException.getDescription());
    }

}
