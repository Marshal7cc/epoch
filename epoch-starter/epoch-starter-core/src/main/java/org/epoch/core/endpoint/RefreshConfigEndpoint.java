package org.epoch.core.endpoint;


import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 * @date 2021/1/27
 * replaced by {@link org.springframework.cloud.endpoint.RefreshEndpoint}
 */
@Deprecated
@RestController
public class RefreshConfigEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshConfigEndpoint.class);

    private ContextRefresher contextRefresher;

    public RefreshConfigEndpoint(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    @PutMapping("/api/v1/system/config")
    public void refresh() {
        Set<String> keys = contextRefresher.refresh();
        LOGGER.info("Received remote refresh request. Keys refreshed: {}", keys);
    }
}
