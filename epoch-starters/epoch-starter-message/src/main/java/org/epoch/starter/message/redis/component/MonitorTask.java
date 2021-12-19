package org.epoch.starter.message.redis.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

/**
 * @author Marshal
 * @date 2020/4/15 监控队列消息任务, 绑定一个listener,
 *        一个listener绑定一个QueueMonitor,当MonitorTask取到消息后,通过listener去反射调用队列监听器的制定方法
 */
@Slf4j
public class MonitorTask implements Runnable {

    /**
     * 100ms.
     */
    private static final long IDLE_SLEEP_TIME = 100L;

    private IQueueMessageListener receiver;
    private RedisTemplate redisTemplate;

    private volatile boolean running = false;

    MonitorTask(IQueueMessageListener receiver, RedisTemplate redisTemplate) {
        this.receiver = receiver;
        this.redisTemplate = redisTemplate;
        Assert.notNull(receiver, "receiver is null.");
        Assert.hasText(receiver.getQueue(), "queue is not valid");
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        running = true;
        Object message;
        while (running) {
            try {
                message = redisTemplate.opsForList().rightPop(receiver.getQueue());
                if (message == null) {
                    sleep(IDLE_SLEEP_TIME);
                    continue;
                }
            } catch (Throwable thr) {
                if (!running) {
                    break;
                }
                if (log.isDebugEnabled()) {
                    log.error("exception occurred while get message from queue [" + receiver.getQueue() + "]", thr);
                }
                continue;
            }
            try {
                receiver.onQueueMessage(message, receiver.getQueue());
            } catch (Throwable thr) {
                if (log.isWarnEnabled()) {
                    log.warn("exception occurred while receiver consume message.", thr);
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("stop monitor:" + this);
        }
    }


    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
