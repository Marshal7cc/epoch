### epoch-starter-message

epoch-starter-message用于系统集成消息中间件，目前提供了两种实现方式：
1.Redis 2.RabbitMQ

### 如何引入
pom.xml
```
        <dependency>
            <groupId>com.marshal</groupId>
            <artifactId>epoch-starter-message</artifactId>
            <version>1.0-RELEASE</version>
        </dependency>
```
1.RabbitMQ

application.yml
```
# epoch-message-starter配置
epoch:
  message:
    enabled: true
    type: rabbit

# RabbitMQ配置
spring:
  rabbitmq:
    host: localhost
    username: guest
    password: guest
    virtual-host: /epoch
    listener:
      simple:
        acknowledge-mode: manual
        retry:
          enabled: true
          max-attempts: 5
          initial-interval: 1000
      direct:
        acknowledge-mode: manual
    publisher-returns: true
    publisher-confirm-type: correlated
```

消息发送：
```
@SpringBootTest
class TestApplicationTests {

    @Autowired
    private MessagePublisher messagePublisher;

    @Test
    void contextLoads() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            MessagePublishRequest request = new MessagePublishRequest();
            request.setRoutingKey("xx");
            request.setExchangeName("xx");
            request.setMessage("xx");
            messagePublisher.publish(request);
        }
    }

}
```

消息监听：
```
    @RabbitListener(queues = {"sys_user_email_fanout_queue"})
    public void on(Message message, Channel channel) throws IOException {
        log.debug("FANOUT_QUEUE_A " + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitListener(queues = {"sys_user_sms_fanout_queue"})
    public void t(Message message, Channel channel) throws IOException {
        log.debug("FANOUT_QUEUE_B " + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
```


2.Redis

application.yml
```
# epoch-message-starter配置
epoch:
  message:
    enabled: true
    type: redis

# Redis常规配置
...

```

消息发送：
```
@SpringBootTest
class TestApplicationTests {

    @Autowired
    private MessagePublisher messagePublisher;

    @Test
    void contextLoads() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            MessagePublishRequest request = new MessagePublishRequest();
            request.setRedisQueue("redisQueue");
            request.setMessage("redis queue test");
            request.setMessage("xx");
            messagePublisher.publish(request);
        }
    }

}
```

消息监听：
```
@Component
@TopicMonitor(channel = "redisChannel", method = "onMessage")
public class RedisChannelConsumer {

    public void onMessage(Object Message) {
        System.out.println(Message);
    }

}
```
