package org.epoch.starter.lock.advisor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.epoch.core.exception.CommonException;
import org.epoch.starter.lock.annotation.Lock;
import org.epoch.starter.lock.provider.LockContextHolder;
import org.epoch.starter.lock.provider.LockInfo;
import org.epoch.starter.lock.provider.LockInfoProvider;
import org.epoch.starter.lock.provider.LockServiceFactory;
import org.epoch.starter.lock.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 切面加锁处理
 *
 * @author Marshal
 * @date 2021/12/7
 */
@Aspect
@Component
public class LockAspectHandler {

    @Autowired
    private LockInfoProvider lockInfoProvider;
    @Autowired
    private LockServiceFactory lockFactory;


    @Around(value = "@annotation(lock)")
    public Object around(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        // Get Lock Attribute.
        LockInfo lockInfo = lockInfoProvider.getLockInfo(joinPoint, lock);
        // Resolve LockService.
        LockService lockService = lockFactory.getLock(lock.lockType());
        // Skip while current thread hold a same lock already.
        LockInfo currentLock = LockContextHolder.currentLock();
        if (lockInfo.equals(currentLock)) {
            return joinPoint.proceed();
        }

        LockContextHolder.setCurrentLock(lockInfo);
        boolean lockResult = false;
        try {
            lockResult = lockService.lock(lockInfo);
            if (lockResult) {
                lockResult = true;
                return joinPoint.proceed();
            } else {
                throw new CommonException("Get lock failed.");
            }
        } finally {
            if (lockResult) {
                lockService.unlock(lockInfo);
                LockContextHolder.clearCurrentLock();
            }
        }
    }
}
