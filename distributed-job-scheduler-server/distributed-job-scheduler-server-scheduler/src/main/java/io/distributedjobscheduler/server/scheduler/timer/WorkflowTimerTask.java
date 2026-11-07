package io.distributedjobscheduler.server.scheduler.timer;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class WorkflowTimerTask extends AbstractTimerTask {

    /**
     * Timer task.
     *
     * @param taskId     taskId
     * @param slotsId    slotsId
     * @param expiration expiration
     */
    public WorkflowTimerTask(Long taskId, Long slotsId, Long expiration) {
        super(taskId, slotsId, expiration);
    }

    @Override
    public void run() {

    }
}
