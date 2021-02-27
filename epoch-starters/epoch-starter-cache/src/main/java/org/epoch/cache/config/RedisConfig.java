package org.epoch.cache.config;

import java.io.Serializable;
import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;

/**
 * Redis配置
 *
 * @author Marshal
 * @date 2018/12/5
 */
@Configuration
public class RedisConfig {

    /**
     * redisTemplate配置-->配置key和value的序列化器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        // 使存入的数据可以直接阅读
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Serializable> limitRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * SpringCache
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 缓存配置对象
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();


        redisCacheConfiguration =
                // 设置缓存的默认超时时间：30分钟
                redisCacheConfiguration.entryTtl(Duration.ofMinutes(30L))
                        // 如果是空值，不缓存
                        .disableCachingNullValues()
                        .serializeKeysWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(keySerializer()))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer((valueSerializer())));

        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }


    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }

    @Bean
    RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    RedisSerializer<Object> valueSerializer() {
        Jackson2JsonRedisSerializer valueSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(new LaissezFaireSubTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        valueSerializer.setObjectMapper(mapper);
        return valueSerializer;
    }

}
