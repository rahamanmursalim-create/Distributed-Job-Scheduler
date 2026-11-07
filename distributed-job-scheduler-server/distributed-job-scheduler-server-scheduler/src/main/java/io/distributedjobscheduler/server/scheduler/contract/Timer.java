package io.distributedjobscheduler.server.scheduler.contract;

import io.distributedjobscheduler.server.scheduler.timer.AbstractTimerTask;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface Timer {

    /**
     * Add tasker
     *
     * @param timerTask
     */
    void add(AbstractTimerTask timerTask);

    /**
     * Advance clock.
     *
     * @param timeout second.
     * @return Boolean
     */
    Boolean advanceClock(Long timeout);

    /**
     * Task size.
     *
     * @return Size.
     */
    Integer size();

    /**
     * Shutdown timer.
     */
    void shuntDown();
}
