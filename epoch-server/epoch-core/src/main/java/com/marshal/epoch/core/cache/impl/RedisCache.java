package com.marshal.epoch.core.cache.impl;

import com.marshal.epoch.core.cache.Cache;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/6/26
 * @desc: Redis缓存实现，存储结构使用Hash结构
 */
@Component
public abstract class RedisCache<T> implements Cache<T>, BeanNameAware {

    private Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private String identifier;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void init() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String sqlId = getSqlId();
        if (StringUtils.isNotEmpty(sqlId)) {
            List<Object> resultList = sqlSession.selectList(sqlId);
            for (Object item : resultList) {
                try {
                    redisTemplate.opsForHash().put(getKeyName(), PropertyUtils.getProperty(item, getHashKeyColumn()).toString(), item);
                } catch (Exception e) {
                    logger.error("init " + getIdentifier() + " error");
                }
            }
        }
    }

    @Override
    public T get(String id) {
        return (T) redisTemplate.opsForHash().get(getKeyName(), id);
    }

    @Override
    public List<T> getAll() {
        List<T> content = (List) redisTemplate.opsForHash().values(getKeyName());
        return content;
    }

    @Override
    public void add(T t) {
        try {
            String id = PropertyUtils.getProperty(t, getHashKeyColumn()).toString();
            redisTemplate.opsForHash().put(getKeyName(), id, t);
        } catch (Exception e) {
            logger.error("add record failed");
        }
    }

    @Override
    public void update(T t) {
        try {
            String id = PropertyUtils.getProperty(t, getHashKeyColumn()).toString();
            redisTemplate.opsForHash().put(getKeyName(), id, t);
        } catch (Exception e) {
            logger.error("update record failed");
        }
    }

    @Override
    public void delete(Object id) {
        if (id instanceof List) {
            List<Object> ids = (List) id;
            ids.forEach(hashKey -> redisTemplate.opsForHash().delete(getKeyName(), hashKey.toString()));
        } else if (id instanceof Long[]) {
            for (Object hashKey : (Object[]) id) {
                redisTemplate.opsForHash().delete(getKeyName(), hashKey.toString());
            }
        } else {
            redisTemplate.opsForHash().delete(getKeyName(), id);
        }
    }

    @Override
    public void refresh() {
        clear();
        init();
    }

    @Override
    public void clear() {
        redisTemplate.opsForHash().delete(getKeyName());
    }

    @Override
    public void setBeanName(String name) {
        this.identifier = name;
    }

    /**
     * 数据来源的sqlId
     *
     * @return
     */
    public abstract String getSqlId();

    /**
     * 获取redis hash 结构的key
     *
     * @return
     */
    public abstract String getKeyName();

    /**
     * 获取hashKey de 取值来源
     *
     * @return
     */
    public abstract String getHashKeyColumn();
}
