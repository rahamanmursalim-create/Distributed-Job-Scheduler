package io.distributedjobscheduler.server.common.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerDTO {
    private Long appId;

    private Long namespaceId;

    private String appName;

    private String workerKey;

    private String address;

    private String protocolType;
}
