package io.distributedjobscheduler.server.cluster.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
@Data
public class WorkerStopReqDTO {
    private String workerKey;
    private String appName;
    private String address;
}
