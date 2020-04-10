package com.marshal.epoch.message.rabbit;

import com.marshal.epoch.message.MessagePublishRequest;
import com.marshal.epoch.message.MessagePublisher;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @auth: Marshal
 * @date: 2020/4/10
 * @desc:
 */
@Data
public class RabbitMessagePublisher implements MessagePublisher {

    private RabbitTemplate rabbitTemplate;

    @Override
    public void publish(MessagePublishRequest request) {
        Object message = request.getMessage();
        String exchangeName = request.getExchangeName();
        String routingKey = request.getRoutingKey();

        Assert.notNull(message, "message can not be null");
        Assert.hasText(exchangeName, "exchange name should have a value");

        //topic and routing mode
        if (!StringUtils.isEmpty(routingKey)) {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        } else {
            //publish/subscribe mode
            rabbitTemplate.convertAndSend(exchangeName, "", message);
        }
    }

}
