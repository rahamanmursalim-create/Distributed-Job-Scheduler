package io.distributedjobscheduler.server.cluster.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
@Data
public class WorkerStartReqDTO {
    private String workerKey;

    private String appName;

    private String address;

    private String version;

    private String protocolType;
}
