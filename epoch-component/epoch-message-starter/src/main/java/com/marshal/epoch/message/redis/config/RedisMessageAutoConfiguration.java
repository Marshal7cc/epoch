package com.marshal.epoch.message.redis.config;

import com.marshal.epoch.message.redis.annotation.TopicMonitor;
import com.marshal.epoch.message.redis.component.RedisMessageQueueListenerContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2020/4/15
 * @desc:
 */
@Slf4j
@ConditionalOnProperty(prefix = "epoch.message", name = {"type"}, havingValue = "redis")
public class RedisMessageAutoConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * redis消息监听器container
     * topic模式
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer redisTopicMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        Map<String, Object> topicMonitorList = applicationContext.getBeansWithAnnotation(TopicMonitor.class);

        //添加所有消息监听适配器
        topicMonitorList.forEach((k, v) -> {
            Class clazz = v.getClass();
            TopicMonitor tm = (TopicMonitor) clazz.getAnnotation(TopicMonitor.class);
            MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(v, tm.method());
            //手动执行afterPropertiesSet方法，否则无法创建invoker,导致空指针
            messageListenerAdapter.afterPropertiesSet();
            container.addMessageListener(messageListenerAdapter, new PatternTopic(tm.channel()));
        });

        return container;
    }

    /**
     * redis消息监听器container
     * 点对点模式
     *
     * @return
     */
    @Bean
    RedisMessageQueueListenerContainer redisQueueMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageQueueListenerContainer container = new RedisMessageQueueListenerContainer();
        container.setRedisTemplate(new StringRedisTemplate(connectionFactory));
        container.init(applicationContext);
        return container;
    }

}
