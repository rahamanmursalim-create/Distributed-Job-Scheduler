package io.distributedjobscheduler.worker.container;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface TaskProcessor {
    /**
     * Start
     */
    void start();

    /**
     * Stop
     */
    void stop();
}
