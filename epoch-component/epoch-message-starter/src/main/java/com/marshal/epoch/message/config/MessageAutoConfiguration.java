package com.marshal.epoch.message.config;

import com.marshal.epoch.message.MessagePublisher;
import com.marshal.epoch.message.enums.MessageTypeEnum;
import com.marshal.epoch.message.properties.MessageProperty;
import com.marshal.epoch.message.rabbit.component.RabbitMessagePublisher;
import com.marshal.epoch.message.rabbit.config.RabbitMessageAutoConfiguration;
import com.marshal.epoch.message.redis.component.RedisMessagePublisher;
import com.marshal.epoch.message.redis.config.RedisMessageAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @auth: Marshal
 * @date: 2020/4/10
 * @desc: epoch-message-starter auto configuration class
 */
@Slf4j
@EnableConfigurationProperties({MessageProperty.class})
@ComponentScan(basePackages = "com.marshal.epoch.message.**")
@ImportAutoConfiguration(classes = {RabbitMessageAutoConfiguration.class, RedisMessageAutoConfiguration.class})
public class MessageAutoConfiguration {

    @Autowired
    private MessageProperty messageProperty;

    @Autowired(required = false)
    private RabbitTemplate rabbitTemplate;

    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.message", name = {"enabled"}, havingValue = "true")
    public MessagePublisher messagePublisher() {
        if (MessageTypeEnum.RABBIT.getType().equalsIgnoreCase(messageProperty.getType())) {
            RabbitMessagePublisher messagePublisher = new RabbitMessagePublisher();
            messagePublisher.setRabbitTemplate(rabbitTemplate);
            return messagePublisher;
        } else if (MessageTypeEnum.REDIS.getType().equalsIgnoreCase(messageProperty.getType())) {
            RedisMessagePublisher redisMessagePublisher = new RedisMessagePublisher();
            redisMessagePublisher.setRedisTemplate(redisTemplate);
            return redisMessagePublisher;
        } else {
            return null;
        }
    }
}
