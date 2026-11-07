package io.distributedjobscheduler.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ServerDelayInstanceStopRequest implements Serializable {
    private String taskId;
}
