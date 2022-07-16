package org.epoch.security.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.epoch.core.constants.BaseConstants;
import org.epoch.security.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求request相关的工具类
 *
 * @author : Marshal
 * @date : 2018/11/29
 */
@Slf4j
public class RequestHelper {
    private static final User ANONYMOUS_USER = new User(BaseConstants.ANONYMOUS_USER_ID, BaseConstants.ANONYMOUS_USER_NAME);

    private static final String FILED_PRINCIPAL = "principal";

    private static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static String getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    public static User getCurrentUser() {
        // todo: fix better
        User user = RequestHelper.currentUser.get();
        if (user == null) {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            if (!(authentication instanceof OAuth2Authentication)) {
                return ANONYMOUS_USER;
            }
            Authentication userAuthentication = ((OAuth2Authentication) authentication).getUserAuthentication();
            if (!(userAuthentication.getDetails() instanceof Map)) {
                return ANONYMOUS_USER;
            }

            Map<String, Object> details = Collections.unmodifiableMap((Map<String, Object>) userAuthentication.getDetails());

            LinkedHashMap<String, String> principal = (LinkedHashMap<String, String>) details.get(FILED_PRINCIPAL);
            user = new User();
            user.setUserId(String.valueOf(principal.get(User.FILED_USER_ID)));
            user.setUsername(principal.get(User.FILED_USER_NAME));

            setCurrentUser(user);
            return user;
        }
        return user;
    }

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public static void removeCurrentUser(User user) {
        currentUser.remove();
    }

    /**
     * 获取RequestAttributes
     *
     * @return
     */
    public static RequestAttributes getRequestAttributes() {
        return RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        if (getRequestAttributes() != null) {
            return ((ServletRequestAttributes) getRequestAttributes()).getRequest();
        } else {
            return null;
        }
    }

    /**
     * 获取HttpServletResponse
     *
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) getRequestAttributes()).getResponse();
    }

    /**
     * 判断是否为ajax请求
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
