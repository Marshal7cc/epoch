package com.marshal.epoch.message;

import lombok.Data;

/**
 * @author Marshal
 */
@Data
public class MessagePublishRequest {

    /**
     * message content
     */
    private Object message;

    /**
     * rabbitMQ params
     */
    private String queueName;
    private String exchangeName;
    private String routingKey;

    /**
     * redis params
     */
    private String redisChannel;
    private String redisQueue;

}
