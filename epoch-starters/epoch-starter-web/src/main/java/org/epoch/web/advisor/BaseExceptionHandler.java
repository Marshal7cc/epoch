package org.epoch.web.advisor;


import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import org.epoch.core.constant.BaseConstants;
import org.epoch.core.exception.BaseException;
import org.epoch.core.rest.Response;
import org.epoch.core.rest.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

/**
 * 全局异常处理器
 *
 * @author Marshal
 * @date 2018/10/26
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @Value("${spring.profiles.active:" + BaseConstants.DEFAULT_ENV + "}")
    private String env;

    /**
     * 默认通用异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Void> handleException(HttpServletRequest request, HandlerMethod method, Exception ex) {
        String message = exceptionMessage("base checked exception", request, method);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(message);
        }
        return Response.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage());
    }

    /**
     * CommonException异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Void> handleException(HttpServletRequest request, HandlerMethod method, BaseException ex) {
        String message = exceptionMessage("epoch common exception", request, method);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(message);
        }
        return Response.error(ex.getMessage(), ex.getMessage());
    }

    /**
     * 拦截 {@link SQLException} 异常信息，返回 “数据操作错误，请联系管理员” 信息
     *
     * @param exception 异常
     * @return ExceptionResponse
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Void> process(HttpServletRequest request, HandlerMethod method, SQLException exception) {
        String message = exceptionMessage("Sql exception", request, method);
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(message, exception);
        }
        return Response.error(String.valueOf(exception.getErrorCode()), message);
    }

    private String exceptionMessage(String message, HttpServletRequest request, HandlerMethod method) {
        return String.format(message + ", Request: {URI=%s, method=%s}", request.getRequestURI(), method.toString());
    }
}
