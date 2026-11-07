package io.distributedjobscheduler.worker.master;

import io.distributedjobscheduler.common.request.ServerInstanceTaskChildListPullRequest;
import io.distributedjobscheduler.common.request.ServerInstanceTaskListPullRequest;
import io.distributedjobscheduler.common.request.ServerStopInstanceTaskRequest;
import io.distributedjobscheduler.common.response.WorkerInstanceTaskChildListPullResponse;
import io.distributedjobscheduler.common.response.WorkerInstanceTaskListPullResponse;
import io.distributedjobscheduler.worker.request.ContainerBatchTaskStatusRequest;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface TaskMaster {

    /**
     * Submit
     */
    void submit();

    /**
     * Get task running status.
     *
     * @return Boolean
     */
    Boolean getRunning();

    /**
     * Destroy task container.
     */
    void destroyTaskContainer();

    /**
     * Complete task.
     *
     * @throws InterruptedException InterruptedException
     */
    void completeTask() throws InterruptedException;

    /**
     * Update status.
     *
     * @param batchRequest batchRequest
     */
    void updateStatus(ContainerBatchTaskStatusRequest batchRequest);

    /**
     * Stop
     *
     * @param type type
     */
    void stop(Integer type);

    /**
     * Pull instance task
     *
     * @param request request
     * @return WorkerInstanceTaskListPullResponse
     */
    WorkerInstanceTaskListPullResponse pullInstanceTaskList(ServerInstanceTaskListPullRequest request);

    /**
     * Pull instance child task
     *
     * @param request request
     * @return WorkerInstanceTaskChildListPullResponse
     */
    WorkerInstanceTaskChildListPullResponse pullInstanceTaskChildList(ServerInstanceTaskChildListPullRequest request);

    /**
     * Stop task
     *
     * @param request request
     */
    void stopTask(ServerStopInstanceTaskRequest request);
}
