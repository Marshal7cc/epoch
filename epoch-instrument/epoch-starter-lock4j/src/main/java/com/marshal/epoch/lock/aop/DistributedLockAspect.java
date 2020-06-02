package com.marshal.epoch.lock.aop;

import com.marshal.epoch.lock.DistributedLocker;
import com.marshal.epoch.lock.annotation.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marshal Yuan
 * @date 2020/5/16
 */
@Aspect
public class DistributedLockAspect {

    @Autowired
    private DistributedLocker distributedLocker;

    @Pointcut("@annotation(com.marshal.epoch.lock.annotation.DistributedLock)")
    public void distributedLockPointcut() {
    }

    @Around("distributedLockPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object object = null;
        DistributedLock distributedLock = getLockInfo(proceedingJoinPoint);
        try {
            boolean success = distributedLocker.tryLock(distributedLock.key(), distributedLock.timeout(),
                    distributedLock.ttl(), distributedLock.fair(), distributedLock.async());
            if (success) {
                object = proceedingJoinPoint.proceed();
            }
        } finally {
            distributedLocker.unlock();
        }
        return object;
    }

    /**
     * 获取注解属性
     *
     * @param proceedingJoinPoint
     * @return
     */
    private DistributedLock getLockInfo(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(DistributedLock.class);
    }

}
