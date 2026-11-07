package io.distributedjobscheduler.server.cluster.service;

import io.distributedjobscheduler.common.request.WorkerDelayStatusRequest;
import io.distributedjobscheduler.server.cluster.executor.WorkerDelayStatusExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
@Service
public class WorkerDelayService {

    private final WorkerDelayStatusExecutor workerDelayStatusExecutor;

    @Autowired
    public WorkerDelayService(WorkerDelayStatusExecutor workerDelayStatusExecutor) {
        this.workerDelayStatusExecutor = workerDelayStatusExecutor;
    }

    /**
     * Handle delay status.
     *
     * @param workerDelayStatusRequest workerDelayStatusRequest
     */
    public void handleDelayStatus(WorkerDelayStatusRequest workerDelayStatusRequest) {
        this.workerDelayStatusExecutor.submit(workerDelayStatusRequest);
    }
}
