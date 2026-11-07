package io.distributedjobscheduler.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ServerCheckTaskMasterRequest implements Serializable {
    private Long jobId;
    private Long jobInstanceId;
}
