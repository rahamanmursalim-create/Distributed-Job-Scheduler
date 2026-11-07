package io.distributedjobscheduler.server.scheduler.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Data
public class TaskChildPullRequestDTO {

    /**
     * Instance id.
     */
    private Long jobInstanceId;

    /**
     * Dispatch version
     */
    private Long dispatchVersion;

    /**
     * Circle id
     */
    private Long circleId;

    /**
     * Task id
     */
    private String taskId;

    /**
     * Worker address
     */
    private String workerAddress;
}
