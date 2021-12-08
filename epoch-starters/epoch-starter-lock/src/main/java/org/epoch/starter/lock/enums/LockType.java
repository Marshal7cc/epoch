package org.epoch.starter.lock.enums;

/**
 * Type of Lock.
 *
 * @author Marshal
 * @date 2021/12/7
 */
public enum LockType {
    REENTRANT,
    FAIR,
    MULTI,
    RED,
    READ,
    WRITE;

    LockType() {
    }
}
