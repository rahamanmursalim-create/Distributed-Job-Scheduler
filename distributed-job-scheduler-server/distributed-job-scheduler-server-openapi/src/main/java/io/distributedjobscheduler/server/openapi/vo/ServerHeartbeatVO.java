package io.distributedjobscheduler.server.openapi.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ServerHeartbeatVO {

    @Schema(description = "Worker address list.")
    private Set<String> workerAddressList;

    @Schema(description = "Cluster version")
    private Long clusterVersion;

    @Schema(description = "Cluster delay version")
    private Long clusterDelayVersion;
}
