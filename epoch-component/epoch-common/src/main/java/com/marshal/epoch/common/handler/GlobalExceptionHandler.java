package com.marshal.epoch.common.handler;


import com.marshal.epoch.common.dto.ResponseEntity;
import com.marshal.epoch.common.exception.CommonException;
import com.marshal.epoch.common.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auth: Marshal
 * @Date: 2018/10/26
 * @desc: 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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
            logger.debug(commonException.getMessage());
        }
        return ResponseUtil.responseErr(commonException.getCode(), commonException.getDescription());
    }

}
