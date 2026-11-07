package io.distributedjobscheduler.server.admin.request.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ListProcessorLogRequest {

    @NotNull
    @Schema(description = "Job instance id", required = true)
    private Long jobId;

    @NotNull
    @Schema(description = "Dispatch version", required = true)
    private Long dispatchVersion;

    @NotNull
    @Schema(description = "Job instance id", required = true)
    private Long jobInstanceId;

    @NotBlank
    @Schema(description = "Execute type: standalone, broadcast, MR", required = true)
    private String executeType;

    @NotNull
    @Schema(description = "Status", required = true)
    private Integer status;

    @NotNull
    @Schema(description = "Loading", required = true)
    private Integer loading;

    @Schema(description = "Processor time, default is zero.", required = true)
    private Long time = 0L;

    @Schema(description = "Page size", required = true)
    private Integer size = 20;
}
