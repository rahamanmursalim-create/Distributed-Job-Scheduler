package io.distributedjobscheduler.server.scheduler.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class DelayTopicPullDTO {
    private Long id;
    private Long pid;
    private String topic;
    private String processorInfo;
    private Integer failRetryTimes;
    private Integer failRetryInterval;
    private Integer executeTimeout;
    private Integer concurrency;
    private Integer failTopicEnable;
    private Integer failTopicConcurrency;
}
