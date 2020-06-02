package com.marshal.epoch.lock.component.redisson;

import com.marshal.epoch.lock.exption.RedissonException;
import lombok.Data;
import org.redisson.api.RedissonClient;

/**
 * @author Marshal Yuan
 * @date 2020/5/16
 */
@Data
public class RedissonClientHolder {

    private RedissonClient redisson;

    public boolean isInitialized() {
        return redisson != null;
    }

    public boolean isStarted() {
        if (redisson == null) {
            throw new RedissonException("Redisson is null");
        }
        return !redisson.isShutdown() && !redisson.isShuttingDown();
    }

    public void validateStartedStatus() throws Exception {
        if (redisson == null) {
            throw new RedissonException("Redisson is null");
        }
        if (!isStarted()) {
            throw new RedissonException("Redisson is closed");
        }
    }

    public void validateClosedStatus() throws Exception {
        if (redisson == null) {
            throw new RedissonException("Redisson is null");
        }
        if (isStarted()) {
            throw new RedissonException("Redisson is started");
        }
    }

    public RedissonClient getRedisson() {
        return redisson;
    }

    public void close() throws Exception {
        validateStartedStatus();
        redisson.shutdown();
    }
}
