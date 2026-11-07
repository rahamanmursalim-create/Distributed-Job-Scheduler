package io.distributedjobscheduler.common.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class JobInstanceDTO {
    private String jobName;
    private String data;
}
