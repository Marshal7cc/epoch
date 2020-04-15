package com.marshal.epoch.message.redis.component;

import com.marshal.epoch.message.MessagePublishRequest;
import com.marshal.epoch.message.MessagePublisher;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @auth: Marshal
 * @date: 2020/4/10
 * @desc: Redis消息发布器
 */
@Data
public class RedisMessagePublisher implements MessagePublisher {

    private RedisTemplate redisTemplate;

    @Override
    public void publish(MessagePublishRequest request) {
        Object message = request.getMessage();
        String redisChannel = request.getRedisChannel();
        String redisQueue = request.getRedisQueue();

        Assert.notNull(message, "message can not be null");

        if (redisChannel != null) {
            redisTemplate.convertAndSend(redisChannel, message);
        } else if (redisQueue != null) {
            redisTemplate.opsForList().leftPush(redisQueue, message);
        } else {
            //do nothing
        }
    }
}
