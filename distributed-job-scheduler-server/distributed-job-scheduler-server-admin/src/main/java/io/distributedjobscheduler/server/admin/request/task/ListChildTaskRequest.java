package io.distributedjobscheduler.server.admin.request.task;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ListChildTaskRequest extends PageRequest {
    @NotBlank
    @Schema(description = "Task id", required = true)
    private String taskId;

    @NotNull
    @Schema(description = "Instance id", required = true)
    private Long jobInstanceId;

    @NotNull
    @Schema(description = "Circle id", required = true)
    private Long circleId;

    @NotNull
    @Schema(description = "Pull", required = true)
    private Integer pull;
}
