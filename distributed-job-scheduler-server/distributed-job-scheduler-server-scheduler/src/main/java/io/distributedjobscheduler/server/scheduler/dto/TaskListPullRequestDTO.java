package io.distributedjobscheduler.server.scheduler.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Data
public class TaskListPullRequestDTO {

    /**
     * Instance id.
     */
    private Long jobInstanceId;

    /**
     * Circle id
     */
    private Long circleId;
}
