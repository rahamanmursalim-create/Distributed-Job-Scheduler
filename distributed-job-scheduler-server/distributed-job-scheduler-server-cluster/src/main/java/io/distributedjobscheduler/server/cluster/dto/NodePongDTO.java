package io.distributedjobscheduler.server.cluster.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class NodePongDTO implements Serializable {

    /**
     * Cluster version.
     */
    private Long clusterVersion;

    /**
     * Response server id.
     */
    private Long serverId;

    /**
     * Whether ping server is know.
     */
    private Boolean knowServer;
}
