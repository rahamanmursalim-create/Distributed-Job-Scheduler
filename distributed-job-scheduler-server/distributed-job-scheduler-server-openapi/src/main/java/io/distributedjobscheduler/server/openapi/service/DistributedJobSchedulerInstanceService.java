package io.distributedjobscheduler.server.openapi.service;

import io.distributedjobscheduler.server.openapi.request.WorkerJobInstanceStatusRequest;
import io.distributedjobscheduler.server.openapi.request.WorkerJobInstanceTaskBatchRequest;
import io.distributedjobscheduler.server.openapi.vo.WorkerJobInstanceStatusVO;
import io.distributedjobscheduler.server.openapi.vo.WorkerJobInstanceTaskBatchVO;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
public interface DistributedJobSchedulerInstanceService {

    /**
     * Handle status
     *
     * @param statusRequest statusRequest
     * @return WorkerJobInstanceStatusVO
     */
    WorkerJobInstanceStatusVO handleStatus(WorkerJobInstanceStatusRequest statusRequest);

    /**
     * Handle tasks
     *
     * @param batchRequest batchRequest
     * @return WorkerJobInstanceTaskBatchVO
     */
    WorkerJobInstanceTaskBatchVO handleTasks(WorkerJobInstanceTaskBatchRequest batchRequest);
}
