package io.distributedjobscheduler.server.openapi.request;

import io.distributedjobscheduler.common.constant.FailStatusEnum;
import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
@Data
public class DelayInstanceStatusRequest {

    private List<DelayInstanceTaskRequest> list;

    @Data
    public static class DelayInstanceTaskRequest {
        /**
         * Topic
         */
        private String topic;

        /**
         * Delay id.
         */
        private Long delayId;

        /**
         * Delay pid
         */
        private Long delayPid;

        /**
         * Task id.
         */
        private String taskId;

        /**
         * Task status.
         */
        private Integer status;

        /**
         * Fail status
         *
         * @see FailStatusEnum#getStatus()
         */
        private Integer failStatus;

        /**
         * Task result.
         */
        private String result;

        /**
         * Worker address
         */
        private String workerAddress;

        /**
         * Complete time
         */
        private Long completeTime;
    }
}
