package org.epoch.rocketmq.core;

import java.util.Objects;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.autoconfigure.ListenerContainerConfiguration;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.epoch.core.exception.BaseException;
import org.epoch.core.util.GenericTypeConverter;
import org.epoch.rocketmq.support.RocketMQConsumerRuntimeListener;
import org.springframework.core.GenericTypeResolver;

/**
 * @author Marshal
 * @since 2022/11/30
 */
@Slf4j
public abstract class RocketMQConsumer<T> implements RocketMQListener<MessageExt> {

    @Setter
    private int maxReconsumeTimes = 16;
    @Setter
    private boolean storeErrorMessage;

    @Override
    @SuppressWarnings("unchecked")
    public void onMessage(MessageExt messageExt) {
        // RocketMQ runtime variables.
        if (this instanceof RocketMQConsumerRuntimeListener) {
            ((RocketMQConsumerRuntimeListener) this).prepareStart(this);
        }

        // Handle message.
        byte[] body = messageExt.getBody();
        String msgId = messageExt.getMsgId();
        int reconsumeTimes = messageExt.getReconsumeTimes();
        log.info("RocketMQ message received, msgId: {}, reconsume time: {}", msgId, reconsumeTimes);

        // Resolve runtime variables.
        if (reconsumeTimes > this.maxReconsumeTimes && storeErrorMessage) {
            storeErrorMessage(messageExt);
        }

        try {
            consumeMessage(GenericTypeConverter.parseObject(new String(body),
                    (Class<T>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), RocketMQConsumer.class))[0]
            ));
        } catch (Exception e) {
            log.info("consume RocketMQ message error, msgId: {}, reconsume time: {}", msgId, reconsumeTimes);
            throw new BaseException(e);
        }
    }

    /**
     * Consume message actually.
     *
     * @param message message body.
     */
    public abstract void consumeMessage(T message);

    /**
     * Store error message into database.
     *
     * @param messageExt
     */
    protected abstract void storeErrorMessage(MessageExt messageExt);

    /**
     * Whether this RocketMQ consumer can start up or not in 'ListenerContainerConfiguration'
     *
     * @return
     * @see ListenerContainerConfiguration
     */
    public boolean canStartUp() {
        return true;
    }
}
