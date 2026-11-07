package io.distributedjobscheduler.worker.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ContainerBatchTaskStatusRequest implements Serializable {
    private Long jobId;
    private Long jobInstanceId;
    private Long circleId;
    private String workerAddress;
    private String masterActorPath;
    private Long deliveryId;
    private List<ContainerTaskStatusRequest> taskStatusList;
}
