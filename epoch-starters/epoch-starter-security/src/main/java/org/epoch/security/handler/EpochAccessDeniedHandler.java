package org.epoch.security.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.epoch.core.util.GenericTypeConverter;
import org.epoch.web.rest.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

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
        httpServletResponse.getWriter().write(GenericTypeConverter.toJSONString(Response.error(TIP_ACCESS_DENIED)));
    }

}
