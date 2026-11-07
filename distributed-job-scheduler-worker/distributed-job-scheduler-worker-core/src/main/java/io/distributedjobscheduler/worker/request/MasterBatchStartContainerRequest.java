package io.distributedjobscheduler.worker.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class MasterBatchStartContainerRequest implements Serializable {
    private Long jobInstanceId;
    private Long jobId;
    private List<MasterStartContainerRequest> startContainerRequests;
}
