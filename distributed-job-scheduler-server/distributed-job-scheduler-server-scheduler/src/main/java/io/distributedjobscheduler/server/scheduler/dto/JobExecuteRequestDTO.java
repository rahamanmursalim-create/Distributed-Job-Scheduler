package io.distributedjobscheduler.server.scheduler.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class JobExecuteRequestDTO {
    private Long id;

    private String params;

    private String extendParams;
}
