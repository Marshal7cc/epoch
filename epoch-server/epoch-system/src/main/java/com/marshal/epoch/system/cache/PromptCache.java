package com.marshal.epoch.system.cache;

import com.marshal.epoch.cache.component.RedisCache;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/6/26
 * @desc:
 */
@Component
public class PromptCache<SysPrompt> extends RedisCache<SysPrompt> {

    private String SQI_ID = "com.marshal.epoch.system.mapper.SysPromptMapper.select";

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
