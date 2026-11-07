package io.distributedjobscheduler.common.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerHeartbeatRequest implements Serializable {
    private Long appId;

    private String appName;

    private String address;

    private String version;

    /**
     * Running job instance ids.
     */
    private List<Long> runningJobInstanceIds;
}
