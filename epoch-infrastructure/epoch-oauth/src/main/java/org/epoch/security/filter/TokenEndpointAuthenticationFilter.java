package org.epoch.security.filter;

import java.io.IOException;
import java.util.Base64;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.epoch.core.constant.BaseConstants;
import org.epoch.core.constant.Digital;
import org.epoch.core.constant.HttpHeader;
import org.epoch.security.constant.Oauth2EndpointConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * /oauth/**前置Filter,当访问oauth/**时进入
 *
 * @author Marshal
 * @date 2020/1/18
 */
@Slf4j
@Component
public class TokenEndpointAuthenticationFilter extends OncePerRequestFilter
        implements Oauth2EndpointConstant, BaseConstants {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getRequestURL().indexOf(OAUTH_TOKEN) != -1) {
            String[] clientDetails = getClientDetails(httpServletRequest);
        }
        doFilter(httpServletRequest, httpServletResponse, filterChain);
    }

    private String[] getClientDetails(HttpServletRequest request) {
        String[] params = null;
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            String basic = header.substring(0, 5);
            if (basic.toLowerCase().contains(HttpHeader.BASIC)) {
                String tmp = header.substring(6);
                String defaultClientDetails = new String(Base64.getDecoder().decode(tmp));
                String[] clientArrays = defaultClientDetails.split(":");
                if (clientArrays.length != Digital.TWO) {
                    return null;
                } else {
                    params = clientArrays;
                }
            }
        }
        String id = request.getParameter("client_id");
        String secret = request.getParameter("client_secret");

        if (header == null && id != null) {
            params = new String[]{id, secret};
        }
        return params;
    }
}
