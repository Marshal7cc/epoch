package org.epoch.lock.provider;

import org.springframework.core.NamedThreadLocal;

/**
 * @author Marshal
 * @since 2021/12/8
 */
public class LockContextHolder {
    private static ThreadLocal<LockInfo> currentLock = new NamedThreadLocal<>("Lock Attributes");

    public static LockInfo currentLock() {
        return currentLock.get();
    }

    public static void setCurrentLock(LockInfo lockInfo) {
        currentLock.set(lockInfo);
    }

    public static void clearCurrentLock() {
        currentLock.remove();
    }
}
