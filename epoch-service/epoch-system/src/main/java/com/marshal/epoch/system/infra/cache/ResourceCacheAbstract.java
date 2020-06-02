package com.marshal.epoch.system.infra.cache;

import com.marshal.epoch.cache.component.AbstractRedisCache;
import com.marshal.epoch.system.domain.entity.SysResource;
import org.springframework.stereotype.Component;

/**
 * @author Marshal
 * @date 2020/4/30
 *
 */
@Component
public class ResourceCacheAbstract extends AbstractRedisCache<SysResource> {

    private static final String KEY = "epoch:cache:resource";
    private static final String HASH_KEY_COLUMN = "url";
    private static final String SQI_ID = "com.marshal.epoch.system.mapper.SysResourceMapper.select";

    @Override
    public String getSqlId() {
        return SQI_ID;
    }

    @Override
    public String getKeyName() {
        return KEY;
    }

    @Override
    public String getHashKeyColumn() {
        return HASH_KEY_COLUMN;
    }
}
