package io.distributedjobscheduler.server.admin.request.delay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class DeleteDelayRequest {
    @NotNull
    @Schema(description = "Primary id", required = true)
    private Long id;

    @NotNull
    @Schema(description = "Delay cid", required = true)
    private Long cid;
}
