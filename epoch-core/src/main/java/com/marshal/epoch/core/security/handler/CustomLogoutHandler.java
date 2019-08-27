package com.marshal.epoch.core.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auth: Marshal
 * @date: 2018/11/29
 * @desc: 登出处理器
 */
@Component
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        //解决spring security默认的CookieClearingLogoutHandler 清除cookie失效问题，手动清除cookie
        Cookie cookie = new Cookie("JSESSIONID", null);
        String cookiePath = httpServletRequest.getContextPath();//默认的处理器后面会加"/",与实际session路径不一致
        if (!StringUtils.hasLength(cookiePath)) {
            cookiePath = "/";
        }
        cookie.setPath(cookiePath);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

}
