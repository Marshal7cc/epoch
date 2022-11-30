package org.epoch.rocketmq.support;

import org.apache.rocketmq.spring.support.RocketMQConsumerLifecycleListener;
import org.epoch.rocketmq.core.RocketMQConsumer;

/**
 * @author Marshal
 * @since 2022/11/30
 */
@SuppressWarnings("rawtypes")
public interface RocketMQConsumerRuntimeListener extends RocketMQConsumerLifecycleListener<RocketMQConsumer> {
}
