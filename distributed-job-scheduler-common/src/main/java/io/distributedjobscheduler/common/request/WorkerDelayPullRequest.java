package io.distributedjobscheduler.common.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerDelayPullRequest implements Serializable {
    private String workerAddress;
    private List<WorkerDelayItemPullRequest> pullItems;
}
