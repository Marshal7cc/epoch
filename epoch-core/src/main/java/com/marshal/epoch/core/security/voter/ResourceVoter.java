package com.marshal.epoch.core.security.voter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 资源投票器
 */
public class ResourceVoter implements AccessDecisionVoter<FilterInvocation> {
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> collection) {
        int result = ACCESS_ABSTAIN;
        assert authentication != null;
        assert filterInvocation != null;
        assert collection != null;

        // 已经 permitAll 的 url 不再过滤(主要是一些资源类 url,通用 url)
        for (ConfigAttribute attribute : collection) {
            if ("permitAll".equals(attribute.toString())) {
                return result;
            }
        }

        //拿到当前请求
        HttpServletRequest request = filterInvocation.getRequest();
        String uri = StringUtils.substringAfter(request.getRequestURI(), request.getContextPath());
        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        if ("".equals(uri)) {
            return ACCESS_ABSTAIN;
        }

        result = ACCESS_ABSTAIN;
        return result;
    }
}
