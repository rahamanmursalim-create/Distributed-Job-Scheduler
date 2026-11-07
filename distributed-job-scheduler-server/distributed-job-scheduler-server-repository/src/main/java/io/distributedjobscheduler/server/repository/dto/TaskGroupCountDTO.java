package io.distributedjobscheduler.server.repository.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Data
public class TaskGroupCountDTO {
    private String parentTaskId;
    private Long count;

    public TaskGroupCountDTO(String parentTaskId, Long count) {
        this.parentTaskId = parentTaskId;
        this.count = count;
    }
}
