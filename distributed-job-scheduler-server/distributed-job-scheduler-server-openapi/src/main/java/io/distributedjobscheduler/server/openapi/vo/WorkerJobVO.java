package io.distributedjobscheduler.server.openapi.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerJobVO {

    @Schema(description = "Delivery id.", required = true)
    private Long deliveryId;
}
