package io.distributedjobscheduler.server.openapi.service;

import io.distributedjobscheduler.server.openapi.request.WorkerHeartbeatRequest;
import io.distributedjobscheduler.server.openapi.request.WorkerStartRequest;
import io.distributedjobscheduler.server.openapi.request.WorkerStopRequest;
import io.distributedjobscheduler.server.openapi.vo.ServerHeartbeatVO;
import io.distributedjobscheduler.server.openapi.vo.ServerWorkerStartVO;
import io.distributedjobscheduler.server.openapi.vo.ServerWorkerStopVO;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface OpenWorkerService {

    /**
     * Worker start
     *
     * @param startRequest start request.
     * @return Register success response.
     */
    ServerWorkerStartVO workerStart(WorkerStartRequest startRequest);

    /**
     * Worker stop
     *
     * @param stopRequest stop request.
     * @return Stop success response.
     */
    ServerWorkerStopVO workerStop(WorkerStopRequest stopRequest);

    /**
     * Worker heartbeat
     *
     * @param workerHeartbeatRequest heartbeat request.
     * @return Current server all worker address
     */
    ServerHeartbeatVO workerHeartbeat(WorkerHeartbeatRequest workerHeartbeatRequest);
}
