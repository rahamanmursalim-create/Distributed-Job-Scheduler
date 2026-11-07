package io.distributedjobscheduler.server.admin.request.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Schema
@Data
public class ExecuteJobRequest {
    @NotNull
    @Schema(description = "Delete job id", required = true)
    private Long id;

    @Schema(description = "Job params")
    private String params;

    @Schema(description = "Job extend params")
    private String extendParams;
}
