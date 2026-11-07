package io.distributedjobscheduler.server.scheduler.wheel;

import io.distributedjobscheduler.server.scheduler.autoconfigure.SchedulerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Component
public class SchedulerWheel extends AbstractWheel {
    private final SchedulerProperties schedulerProperties;

    @Autowired
    public SchedulerWheel(SchedulerProperties schedulerProperties) {
        this.schedulerProperties = schedulerProperties;
    }

    @Override
    public void start() {
        int wheelSize = this.schedulerProperties.getScheduler().getTimingWheelSize();
        if (wheelSize < 1) {
            throw new RuntimeException(String.format("Scheduler timingWheelSize invalid! timingWheelSize=%d", wheelSize));
        }

        this.createWheel(wheelSize, "scheduler");
    }

    @Override
    public void stop() {
        this.shutdownWheel("scheduler");
    }
}
