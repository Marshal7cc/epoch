package com.marshal.epoch.message;

import lombok.Data;

@Data
public class MessagePublishRequest {

    private Object message;

    private String queueName;

    private String exchangeName;

    private String routingKey;

}
