package io.distributedjobscheduler.worker.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class DelayInstanceDTO {
    private String topic;
    private String taskId;
    private Long delayId;
    private Long delayPid;
    private String delayParams;
    private String delayExtra;
    private String processorInfo;
    private Integer failRetryTimes;
    private Integer failRetryInterval;
    private Integer executeTimeout;
    private Integer concurrency;
}
