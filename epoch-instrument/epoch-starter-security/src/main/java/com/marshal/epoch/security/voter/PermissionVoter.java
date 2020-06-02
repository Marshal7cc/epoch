package com.marshal.epoch.security.voter;


import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * 权限投票器
 *
 * @author Marshal
 * @date 2020/4/23
 */
public class PermissionVoter implements AccessDecisionVoter<FilterInvocation> {

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation,
                    Collection<ConfigAttribute> collection) {
        return ACCESS_ABSTAIN;
    }
}
