package com.marshal.epoch.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.marshal.epoch.core.domain.VerifiedUser;
import lombok.extern.slf4j.Slf4j;
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

    private static final String FILED_USER_ID = "userId";
    private static final String FILED_USER_NAME = "userName";

    private static ThreadLocal<VerifiedUser> verifiedUser = new ThreadLocal<>();

    public static VerifiedUser getCurrentUser() {
        VerifiedUser user = RequestHelper.verifiedUser.get();
        if (user == null) {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();

            UsernamePasswordAuthenticationToken userAuthentication = (UsernamePasswordAuthenticationToken) ReflectUtil
                    .getFieldValue(authentication, FILED_USER_AUTHENTICATION);
            LinkedHashMap details = (LinkedHashMap<String, Object>) userAuthentication.getDetails();

            LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) details.get(FILED_PRINCIPAL);
            user = new VerifiedUser();
            user.setUserId(Long.parseLong(String.valueOf(map.get(FILED_USER_ID))));
            user.setUsername(map.get(FILED_USER_NAME));

            setCurrentUser(user);
            return user;
        }
        return user;
    }

    public static void setCurrentUser(VerifiedUser user) {
        verifiedUser.set(user);
    }

    public static void removeCurrentUser(VerifiedUser user) {
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

    /**
     * 解析post请求的参数
     *
     * @param request
     * @return
     */
    public static JSONObject parseRequestBody(HttpServletRequest request) {
        BufferedReader reader = null;
        char[] reqStr = new char[1000];
        try {
            reader = request.getReader();
            reader.read(reqStr);
        } catch (IOException e) {
            logger.error("parse requestBody fail !");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                logger.error("close reader fail!");
            }
        }
        JSONObject result = JSON.parseObject(new String(reqStr));
        return result;
    }

}
