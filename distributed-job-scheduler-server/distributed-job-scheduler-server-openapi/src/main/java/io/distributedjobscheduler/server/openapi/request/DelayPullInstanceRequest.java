package io.distributedjobscheduler.server.openapi.request;

import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
@Data
public class DelayPullInstanceRequest {
    private String workerAddress;
    private List<DelayPullTopicItemRequest> list;

    @Data
    public static class DelayPullTopicItemRequest {
        private String topic;
        private Integer size;
    }
}
