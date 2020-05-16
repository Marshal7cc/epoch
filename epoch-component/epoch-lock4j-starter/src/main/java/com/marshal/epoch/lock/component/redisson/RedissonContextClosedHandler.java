package com.marshal.epoch.lock.component.redisson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * @author: Marshal Yuan
 * @since: 2020/5/16
 */
public class RedissonContextClosedHandler implements ApplicationListener<ContextClosedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RedissonContextClosedHandler.class);

    @Autowired
    private RedissonClientHolder redissonClientHolder;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        try {
            redissonClientHolder.close();
            logger.debug("redisson conncetion closed...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
