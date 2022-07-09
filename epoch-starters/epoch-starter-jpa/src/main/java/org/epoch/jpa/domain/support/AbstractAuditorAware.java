package org.epoch.jpa.domain.support;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * @author Marshal
 * @since 2022/7/9
 */
public abstract class AbstractAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(getUserId());
    }

    /**
     * Get current auditor.
     *
     * @return
     */
    protected abstract String getUserId();
}
