package org.epoch.cache.component;

import java.util.List;

/**
 * 缓存
 * <p>
 * 目前的实现有RedisCache
 * </p>
 *
 * @author Marshal
 * @date 2019/6/25
 */
public interface Cache<T> {

    /**
     * 缓存初始化加载
     */
    void init();

    /**
     * 缓存增删改查
     *
     * @param id
     * @return
     */
    T get(String id);

    /**
     * 获取所有缓存
     *
     * @return
     */
    List<T> getAll();

    /**
     * 添加缓存
     * @param t
     */
    void add(T t);

    /**
     * 更新缓存
     * @param t
     */
    void update(T t);

    /**
     * 删除缓存
     * @param id
     */
    void delete(Object id);

    /**
     * 缓存刷新
     */
    void refresh();

    /**
     * 清除缓存
     */
    void clear();


    /**
     * 缓存唯一标识
     *
     * @return
     */
    String getIdentifier();

}
