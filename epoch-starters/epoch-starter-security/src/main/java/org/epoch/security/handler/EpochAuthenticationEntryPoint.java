package org.epoch.security.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.epoch.core.rest.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author Marshal
 * @date 2020/1/15
 */
public class EpochAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final String TIP_UNAUTHORIZED = "epoch.tip-unauthorized";

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        String message = e.getMessage() == null ? TIP_UNAUTHORIZED : e.getMessage();

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write(JSON.toJSONString(Response.fail(message)));
    }

}
