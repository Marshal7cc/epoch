package org.epoch.core.base;


import static org.epoch.core.base.BaseConstants.DEFAULT_ENV;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import org.epoch.core.exception.CommonException;
import org.epoch.core.rest.Response;
import org.epoch.core.rest.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

/**
 * 全局异常处理器
 *
 * @author Marshal
 * @date 2018/10/26
 */
@ControllerAdvice
public class BaseExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @Value("${spring.profiles.active:" + DEFAULT_ENV + "}")
    private String env;

    /**
     * 默认通用异常处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception ex) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(ex.getMessage());
        }
        return Response.fail(BaseConstants.ResponseCode.FAIL, ex.getMessage());
    }

    /**
     * CommonException异常处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity handleException(CommonException ex) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(ex.getMessage());
        }
        return Response.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 拦截 {@link SQLException} 异常信息，返回 “数据操作错误，请联系管理员” 信息
     *
     * @param exception 异常
     * @return ExceptionResponse
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity process(HttpServletRequest request, HandlerMethod method, SQLException exception) {
        String message = exceptionMessage("Sql exception", request, method);
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(message, exception);
        }
        return Response.fail(String.valueOf(exception.getErrorCode()), message);
    }

    private String exceptionMessage(String message, HttpServletRequest request, HandlerMethod method) {
        return String.format(message + ", Request: {URI=%s, method=%s}", request.getRequestURI(), method.toString());
    }
}
