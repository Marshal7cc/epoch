package org.epoch.security.translator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.epoch.core.rest.Response;
import org.epoch.security.constants.SecurityConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @author Marshal
 * @date 2020/1/15
 */
@Slf4j
@Component
public class EpochExceptionTranslator implements WebResponseExceptionTranslator, SecurityConstants {

    @Override
    public ResponseEntity<?> translate(Exception e) {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        org.epoch.core.rest.ResponseEntity response = new org.epoch.core.rest.ResponseEntity();
        String message = "认证失败";
        log.error(message, e);
        if (e instanceof UnsupportedGrantTypeException) {
            message = "不支持该认证类型";
        }
        if (e instanceof InvalidTokenException
                && StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token (expired)")) {
            message = "刷新令牌已过期，请重新登录";
        }
        if (e instanceof InvalidScopeException) {
            message = "不是有效的scope值";
        }
        if (e instanceof InvalidGrantException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), ResponseMessage.INVALID_REFRESH_TOKEN)) {
                message = "refresh token无效";
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), ResponseMessage.LOCKER)) {
                message = "用户已被锁定，请联系管理员";
            }
            message = "用户名或密码错误";
        }
        return status.body(Response.fail(message));
    }
}
