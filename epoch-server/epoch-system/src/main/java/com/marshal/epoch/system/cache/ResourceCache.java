package com.marshal.epoch.system.cache;

import com.marshal.epoch.cache.component.RedisCache;
import com.marshal.epoch.system.entity.SysResource;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2020/4/30
 * @desc:
 */
@Component
public class ResourceCache extends RedisCache<SysResource> {

    private static final String KEY = "epoch:cache:resource";
    private static final String HASH_KEY_COLUMN = "url";
    private String SQI_ID = "com.marshal.epoch.system.mapper.SysResourceMapper.select";

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
