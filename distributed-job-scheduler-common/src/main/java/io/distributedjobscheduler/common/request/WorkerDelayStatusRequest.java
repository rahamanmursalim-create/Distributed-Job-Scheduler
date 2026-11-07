package io.distributedjobscheduler.common.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerDelayStatusRequest implements Serializable {

    /**
     * Delivery id.
     */
    private Long deliveryId;

    /**
     * Delay task list.
     */
    private List<WorkerDelayTaskRequest> taskList;
}
