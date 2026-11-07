package io.distributedjobscheduler.common.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Data
public class WorkerInstanceTaskListPullResponse implements Serializable {
    /**
     * Task list.
     */
    private List<WorkerInstanceTaskResponse> taskList;
}
