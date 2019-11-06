package com.marshal.epoch.core.handler;


import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.util.ResponseUtil;
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
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e) {
        if (logger.isDebugEnabled()) {
            logger.debug(e.getMessage());
        }
        return ResponseUtil.responseErr(e.getMessage());
    }

}
