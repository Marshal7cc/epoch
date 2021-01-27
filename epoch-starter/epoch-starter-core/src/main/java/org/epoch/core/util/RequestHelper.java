package org.epoch.core.util;

import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.epoch.core.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private static final Logger logger = LoggerFactory.getLogger(RequestHelper.class);

    private static final String FILED_USER_AUTHENTICATION = "userAuthentication";
    private static final String FILED_PRINCIPAL = "principal";

    private static ThreadLocal<User> verifiedUser = new ThreadLocal<>();

    public static User getCurrentUser() {
        // todo: fix better
        User user = RequestHelper.verifiedUser.get();
        if (user == null) {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();

            UsernamePasswordAuthenticationToken userAuthentication = (UsernamePasswordAuthenticationToken) ReflectUtil
                    .getFieldValue(authentication, FILED_USER_AUTHENTICATION);
            LinkedHashMap details = (LinkedHashMap<String, Object>) userAuthentication.getDetails();

            LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) details.get(FILED_PRINCIPAL);
            user = new User();
            user.setUserId(Long.parseLong(String.valueOf(map.get(User.FILED_USER_ID))));
            user.setUsername(map.get(User.FILED_USER_NAME));

            setCurrentUser(user);
            return user;
        }
        return user;
    }

    public static void setCurrentUser(User user) {
        verifiedUser.set(user);
    }

    public static void removeCurrentUser(User user) {
        verifiedUser.remove();
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
