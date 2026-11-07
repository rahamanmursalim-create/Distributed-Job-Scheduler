package io.distributedjobscheduler.server.openapi.request;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
@Data
public class DelayPullTopicRequest {
    private String appName;
}
