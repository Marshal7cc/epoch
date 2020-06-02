package com.marshal.epoch.security.handler;

import com.alibaba.fastjson.JSON;
import com.marshal.epoch.core.rest.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Marshal
 * @date 2020/1/15
 */
@Component
public class EpochAccessDeniedHandler implements AccessDeniedHandler {

    private static final String TIP_ACCESS_DENIED = "epoch.tip-accessDenied";

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.getWriter().write(JSON.toJSONString(Response.fail(TIP_ACCESS_DENIED)));
    }

}
