package com.marshal.epoch.message.config;

import com.marshal.epoch.message.MessagePublishRequest;
import com.marshal.epoch.message.MessagePublisher;
import com.marshal.epoch.message.enums.MessageTypeEnum;
import com.marshal.epoch.message.properties.MessageProperty;
import com.marshal.epoch.message.rabbit.RabbitMessagePublisher;
import com.marshal.epoch.message.redis.RedisMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @auth: Marshal
 * @date: 2020/4/10
 * @desc: epoch-message-starter auto configuration class
 */
@Slf4j
@EnableConfigurationProperties({MessageProperty.class})
@ComponentScan(basePackages = "com.marshal.epoch.message.**")
public class MessageAutoConfiguration {

    @Autowired
    private MessageProperty messageProperty;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.message", name = {"enabled"}, havingValue = "true")
    public MessagePublisher messagePublisher() {
        if (MessageTypeEnum.RABBIT.getType().equalsIgnoreCase(messageProperty.getType())) {
            RabbitMessagePublisher messagePublisher = new RabbitMessagePublisher();
            messagePublisher.setRabbitTemplate(rabbitTemplate(connectionFactory));
            return messagePublisher;
        } else if (MessageTypeEnum.REDIS.getType().equalsIgnoreCase(messageProperty.getType())) {
            return new RedisMessagePublisher();
        } else {
            return null;
        }
    }

    /**
     * 定制化RabbitTemplate
     * <p>
     * 此处为模版类定义 Jackson消息转换器
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     *
     * @return the rabbit template
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "epoch.message", name = {"type"}, havingValue = "rabbit")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 使用jackson 消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        rabbitTemplate.setEncoding("UTF-8");

        // 消息发送失败返回到队列中，yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        // 消息确认，yml需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.debug("消息发送到exchange成功,id: {}", correlationData.getId());
            } else {
                log.debug("消息发送到exchange失败,原因: {}", cause);
            }
        });

        return rabbitTemplate;
    }
}
