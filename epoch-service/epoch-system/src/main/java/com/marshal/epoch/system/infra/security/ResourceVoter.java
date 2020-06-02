package com.marshal.epoch.system.infra.security;

import com.marshal.epoch.security.voter.PermissionVoter;
import com.marshal.epoch.cache.component.Cache;
import com.marshal.epoch.cache.component.CacheManager;
import com.marshal.epoch.system.domain.entity.SysResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 资源投票器
 * @author Marshal
 */
@Slf4j
@Component
public class ResourceVoter extends PermissionVoter {

    private static final String RESOURCE_CACHE_KEY = "resourceCache";

    @Autowired
    private CacheManager cacheManager;

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
        int result = ACCESS_DENIED;
        assert authentication != null;
        assert filterInvocation != null;
        assert collection != null;

        if (true) {
            return ACCESS_ABSTAIN;
        }

        // 已经 permitAll 的 url 不再过滤(主要是一些资源类 url,通用 url)
        for (ConfigAttribute attribute : collection) {
            if (attribute.toString().contains("permitAll")) {
                return ACCESS_ABSTAIN;
            }
        }

        //拿到当前请求
        HttpServletRequest request = filterInvocation.getRequest();
        String uri = StringUtils.substringAfter(request.getRequestURI(), request.getContextPath());

        if ("".equals(uri)) {
            return ACCESS_ABSTAIN;
        }

        Cache<SysResource> cache = cacheManager.getCache(RESOURCE_CACHE_KEY);
        SysResource sysResource = cache.get(uri);
        if (sysResource == null) {
            log.debug("access denied,uri : {} not registered", uri);
            return ACCESS_DENIED;
        }

        result = ACCESS_ABSTAIN;
        return result;
    }
}
