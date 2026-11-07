package io.distributedjobscheduler.server.scheduler.contract;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface DelayScheduler {

    /**
     * Delay scheduler start.
     */
    void start();

    /**
     * Delay scheduler stop.
     */
    void stop();

    /**
     * Refresh
     */
    void refresh();
}
