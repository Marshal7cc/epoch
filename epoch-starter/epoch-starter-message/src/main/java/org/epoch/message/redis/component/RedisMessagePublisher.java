package org.epoch.message.redis.component;

import org.epoch.message.MessagePublishRequest;
import org.epoch.message.MessagePublisher;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

/**
 * @author Marshal
 * @date 2020/4/10
 *  Redis消息发布器
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
