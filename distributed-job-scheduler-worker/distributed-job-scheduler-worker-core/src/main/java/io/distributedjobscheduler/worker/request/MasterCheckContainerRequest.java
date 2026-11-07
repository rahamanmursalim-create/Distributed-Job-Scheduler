package io.distributedjobscheduler.worker.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Data
public class MasterCheckContainerRequest implements Serializable {
    private Long jobId;

    private Long jobInstanceId;
}
