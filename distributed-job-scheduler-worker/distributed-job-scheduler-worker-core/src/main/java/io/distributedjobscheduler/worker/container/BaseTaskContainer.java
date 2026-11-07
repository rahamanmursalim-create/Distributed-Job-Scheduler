package io.distributedjobscheduler.worker.container;

import io.distributedjobscheduler.worker.request.MasterStartContainerRequest;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public abstract class BaseTaskContainer implements TaskContainer {
    protected MasterStartContainerRequest startRequest;

    public BaseTaskContainer(MasterStartContainerRequest startRequest) {
        this.startRequest = startRequest;
    }

    @Override
    public void stopTask(String taskId) {
        TaskContainerManager.INSTANCE.stopAndRemoveTask(taskId);
    }
}
