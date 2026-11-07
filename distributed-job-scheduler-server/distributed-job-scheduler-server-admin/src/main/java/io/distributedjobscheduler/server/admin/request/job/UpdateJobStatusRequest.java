package io.distributedjobscheduler.server.admin.request.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class UpdateJobStatusRequest {

    @NotNull
    @Schema(description = "Update job id", required = true)
    private Long id;

    @NotNull
    @Schema(description = "New job status", required = true)
    private Integer status;
}
