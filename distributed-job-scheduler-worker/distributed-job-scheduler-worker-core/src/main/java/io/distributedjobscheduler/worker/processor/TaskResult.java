package io.distributedjobscheduler.worker.processor;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class TaskResult {
    private Long jobInstanceId;
    private Long circleId;
    private String taskId;
    private String parentTaskId;
    private String result;
    private String taskName;
    private Integer status;
}
