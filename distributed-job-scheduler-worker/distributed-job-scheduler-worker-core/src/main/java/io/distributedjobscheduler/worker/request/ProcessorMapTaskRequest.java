package io.distributedjobscheduler.worker.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ProcessorMapTaskRequest implements Serializable {
    private Long jobId;
    private Long jobInstanceId;
    private Long taskId;
    private String parentTaskName;
    private String taskName;
    private Integer initValueId;
    private Integer taskNum;
    private List<byte[]> tasks;
}
