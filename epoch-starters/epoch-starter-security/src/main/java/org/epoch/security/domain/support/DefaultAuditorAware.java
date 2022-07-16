package org.epoch.security.domain.support;

import java.util.Optional;

import org.epoch.security.util.RequestHelper;
import org.springframework.data.domain.AuditorAware;

/**
 * @author Marshal
 * @since 2022/7/16
 */
public class DefaultAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(RequestHelper.getCurrentUserId());
    }
}
