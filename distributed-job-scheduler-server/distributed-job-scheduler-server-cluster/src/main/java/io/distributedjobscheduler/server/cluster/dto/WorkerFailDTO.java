package io.distributedjobscheduler.server.cluster.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerFailDTO  implements Serializable {

    /**
     * Cluster version.
     */
    private Long clusterVersion;
}
