package com.marshal.epoch.system.infra.cache;

import com.marshal.epoch.cache.component.AbstractRedisCache;
import org.springframework.stereotype.Component;

/**
 * @author Marshal
 * @date 2019/6/26
 *
 */
@Component
public class PromptCacheAbstract<SysPrompt> extends AbstractRedisCache<SysPrompt> {

    private static final String SQI_ID = "com.marshal.epoch.system.mapper.SysPromptMapper.select";

    private static final String KEY = "epoch:cache:prompt";

    private static final String HASH_KEY_COLUMN = "promptId";

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
