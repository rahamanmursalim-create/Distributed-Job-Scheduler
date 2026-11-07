package io.distributedjobscheduler.common.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
@Data
public class WorkerJobInstanceTaskBatchRequest implements Serializable {

    /**
     * Delivery id.
     */
    private Long deliveryId;

    /**
     * Job instance task list.
     * Aggregation many circle task, if second delay task.
     */
    private List<WorkerJobInstanceTaskRequest> taskRequestList;
}
