package io.distributedjobscheduler.server.admin.request.delay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ListDelayInstanceLogRequest {

    @NotBlank
    @Schema(description = "Task id", required = true)
    private String taskId;

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
