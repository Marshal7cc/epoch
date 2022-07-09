package org.epoch.metric.thread;

import org.epoch.metric.util.ThreadUtilities;

/**
 * @author Marshal
 */
public class ThreadStateBeanImpl implements ThreadStateBean {

    @Override
    public int getThreadStatusNEWCount() {
        return getStatusCount(Thread.State.NEW);
    }

    @Override
    public int getThreadStatusRUNNABLECount() {
        return getStatusCount(Thread.State.RUNNABLE);
    }

    @Override
    public int getThreadStatusBLOCKEDCount() {
        return getStatusCount(Thread.State.BLOCKED);
    }

    @Override
    public int getThreadStatusWAITINGCount() {
        return getStatusCount(Thread.State.WAITING);
    }

    @Override
    public int getThreadStatusTIMEDWAITINGCount() {
        return getStatusCount(Thread.State.TIMED_WAITING);
    }

    @Override
    public int getThreadStatusTERMINATEDCount() {
        return getStatusCount(Thread.State.TERMINATED);
    }

    private int getStatusCount(Thread.State state) {
        Thread[] threads = ThreadUtilities.getAllThreads(state);

        return threads.length;
    }
}
