package org.epoch.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.epoch.core.rest.Response;
import org.epoch.security.constant.Oauth2EndpointConstant;
import org.epoch.security.exception.ValidateCodeException;
import org.epoch.security.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Marshal
 * @date 2020/1/29
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (needVerify(httpServletRequest)) {
            try {
                validateCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (ValidateCodeException e) {
                Response.error(httpServletResponse, e.getMessage());
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private boolean needVerify(HttpServletRequest request) {
        RequestMatcher matcher = new AntPathRequestMatcher(Oauth2EndpointConstant.OAUTH_TOKEN, HttpMethod.POST.toString());
        return matcher.matches(request)
                && StringUtils.equalsIgnoreCase(request.getParameter("grant_source"), "web")
                && StringUtils.equalsIgnoreCase(request.getParameter("grant_type"), "password");
    }

    private void validateCode(HttpServletRequest httpServletRequest) throws ValidateCodeException {
        String code = httpServletRequest.getParameter("validateCode");
        String key = httpServletRequest.getParameter("validateCodeKey");
        validateCodeService.check(key, code);
    }
}
