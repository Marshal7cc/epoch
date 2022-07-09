package org.epoch.metric.thread;

/**
 * Query thread size by thread state.
 */
public interface ThreadStateBean {

    int getThreadStatusNEWCount();

    int getThreadStatusRUNNABLECount();

    int getThreadStatusBLOCKEDCount();

    int getThreadStatusWAITINGCount();

    int getThreadStatusTIMEDWAITINGCount();

    int getThreadStatusTERMINATEDCount();
}
