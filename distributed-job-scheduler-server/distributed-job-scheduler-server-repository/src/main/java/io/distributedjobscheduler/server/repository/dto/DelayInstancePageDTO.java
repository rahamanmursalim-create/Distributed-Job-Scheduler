package io.distributedjobscheduler.server.repository.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class DelayInstancePageDTO {
    private Long namespaceId;

    private Long appId;

    private Long delayId;

    private String taskId;

    private Integer status;

    private Integer page;

    private Integer size;

    private Long beginTime;

    private Long endTime;
}
