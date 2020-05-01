package com.marshal.epoch.security.handler;

import com.marshal.epoch.common.util.RequestHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auth: Marshal
 * @date: 2018/11/29
 * @desc: 认证失败处理器
 */
@Component
public class CustomAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        if (RequestHelper.isAjaxRequest(httpServletRequest)) {
            httpServletResponse.getWriter().write("没有权限！");
        } else {
            httpServletResponse.sendRedirect("login");
        }
    }
}
