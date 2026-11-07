package io.distributedjobscheduler.worker.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class MasterDestroyContainerRequest implements Serializable {
    private Long deliveryId;

    private Long jobId;

    private Long jobInstanceId;

    private String workerAddress;
}
