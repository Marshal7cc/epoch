package org.epoch.iam.infra.cache;

import org.epoch.cache.component.AbstractRedisCache;
import org.epoch.iam.domain.entity.SysResource;
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
    private static final String SQI_ID = "org.epoch.iam.mapper.SysResourceMapper.select";

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
