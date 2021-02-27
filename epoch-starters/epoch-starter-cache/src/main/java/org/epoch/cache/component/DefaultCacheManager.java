package org.epoch.cache.component;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.epoch.core.listener.ContextRefreshedListener;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Marshal
 * @date 2019/6/25
 */
@Component
@Slf4j
public class DefaultCacheManager implements CacheManager, ContextRefreshedListener {

    /**
     * 承载所有Cache
     */
    private Map<String, Cache> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Cache getCache(String identifier) {
        Cache cache = cacheMap.get(identifier);
        if (cache == null) {
            log.error("can not get this cache of identifier" + identifier);
        }
        return cache;
    }

    @Override
    public void addCache(Cache cache) {
        if (StringUtils.isNoneEmpty(cache.getIdentifier())) {
            cacheMap.put(cache.getIdentifier(), cache);
        } else {
            log.error("identifier can not be null");
        }
    }

    @Override
    public void deleteCache(String identifier) {
        cacheMap.remove(identifier);
    }

    @Override
    public void refreshAll() {
        cacheMap.forEach((k, v) -> v.refresh());
    }

    @Override
    public void contextInitialized(ApplicationContext applicationContext) {

        Map<String, Cache> beans = applicationContext.getBeansOfType(Cache.class);

        beans.forEach((k, v) -> {
            if (StringUtils.isBlank(v.getIdentifier())) {
                this.cacheMap.put(k, v);
            } else {
                this.cacheMap.put(v.getIdentifier(), v);
            }

            v.init();

        });
    }
}
