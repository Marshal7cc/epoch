package org.epoch.starter.message.config;

import lombok.extern.slf4j.Slf4j;
import org.epoch.starter.message.MessagePublisher;
import org.epoch.starter.message.enums.MessageTypeEnum;
import org.epoch.starter.message.properties.MessageProperty;
import org.epoch.starter.message.rabbit.component.RabbitMessagePublisher;
import org.epoch.starter.message.rabbit.config.RabbitMessageAutoConfiguration;
import org.epoch.starter.message.redis.component.RedisMessagePublisher;
import org.epoch.starter.message.redis.config.RedisMessageAutoConfiguration;
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
 * @author Marshal
 * @date 2020/4/10
 *  epoch-starter-message auto configuration class
 */
@Slf4j
@EnableConfigurationProperties({MessageProperty.class})
@ComponentScan(basePackages = "org.epoch.message.**")
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
