package io.distributedjobscheduler.server.cluster.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
@Data
public class WorkerStartRespDTO {
    private Long appId;
    private String appName;
    private Set<String> workerAddressList;
}
