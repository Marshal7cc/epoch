package com.marshal.epoch.core.cache.impl;


import com.marshal.epoch.core.cache.Cache;
import com.marshal.epoch.core.cache.CacheManager;
import com.marshal.epoch.core.listener.ContextRefreshedListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/6/25
 * @desc:
 */
@Component
@Slf4j
public class CacheManagerImpl implements CacheManager, ContextRefreshedListener {

    /**
     * 承载所有Cache
     */
    private Map<String, Cache> cacheMap = new HashMap<>();

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
