package com.marshal.epoch.core.cache;

/**
 * @auth: Marshal
 * @date: 2019/6/25
 * @desc: 缓存管理器
 */
public interface CacheManager {

    /**
     * 根据key获取指定缓存
     *
     * @param identifier
     * @return
     */
    Cache getCache(String identifier);

    /**
     * 添加缓存
     *
     * @param cache
     */
    void addCache(Cache cache);

    /**
     * 删除指定缓存
     *
     * @param identifier
     */
    void deleteCache(String identifier);

    /**
     * 刷新所有缓存
     */
    void refreshAll();


}
