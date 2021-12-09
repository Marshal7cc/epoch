package org.epoch.starter.lock.provider;

import java.util.EnumMap;

import org.epoch.core.util.ApplicationContextHolder;
import org.epoch.starter.lock.enums.LockType;
import org.epoch.starter.lock.service.LockService;
import org.epoch.starter.lock.service.impl.*;


/**
 * LockService Provider
 *
 * @author Marshal
 * @date 2021/12/7
 */
public class LockServiceFactory {

    private static final EnumMap<LockType, Class<?>> serviceMap = new EnumMap<>(LockType.class);

    static {
        serviceMap.put(LockType.REENTRANT, ReentrantLockServiceImpl.class);
        serviceMap.put(LockType.FAIR, FairLockServiceImpl.class);
        serviceMap.put(LockType.READ, ReadLockServiceImpl.class);
        serviceMap.put(LockType.WRITE, WriteLockServiceImpl.class);
        serviceMap.put(LockType.RED, RedLockServiceImpl.class);
    }

    /**
     * @param lockType 锁类
     * @return LockService
     */
    public LockService getLock(LockType lockType) {
        return (LockService) ApplicationContextHolder.getContext().getBean(serviceMap.get(lockType));
    }
}
