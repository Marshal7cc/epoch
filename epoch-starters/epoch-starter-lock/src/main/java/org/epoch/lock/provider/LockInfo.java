package org.epoch.lock.provider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Attribute of Lock.
 *
 * @author Marshal
 * @date 2021/12/7
 */
@Data
@AllArgsConstructor
public class LockInfo {
    private String name;
    private long waitTime;
    private long leaseTime;
    private TimeUnit timeUnit;
    private List<String> keys;
}
