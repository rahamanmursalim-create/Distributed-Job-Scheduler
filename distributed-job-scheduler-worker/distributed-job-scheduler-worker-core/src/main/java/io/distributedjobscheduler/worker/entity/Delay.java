package io.distributedjobscheduler.worker.entity;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class Delay {
    private Long id;
    private String topic;
    private Integer pullSize;
    private Long pullTime;
    private Long createTime;
    private Long updateTime;
}
