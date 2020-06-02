package com.marshal.epoch.message.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @author Marshal
 * @date 2020/4/15
 *
 */
@Slf4j
@ConditionalOnProperty(prefix = "epoch.message", name = {"type"}, havingValue = "rabbit")
public class RabbitMessageAutoConfiguration {

    /**
     * 定制化RabbitTemplate
     * <p>
     * 可被再度自定义以实现消息丢失、拒绝机制等
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
                log.debug("消息发送到exchange成功,data: {}", correlationData);
            } else {
                log.debug("消息发送到exchange失败,原因: {}", cause);
            }
        });

        return rabbitTemplate;
    }
}
