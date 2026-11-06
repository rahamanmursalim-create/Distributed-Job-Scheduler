package io.distributedjobscheduler.server.admin.request.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.7
 */
@Data
public class StopTaskRequest {
    @NotNull
    @Schema(description = "Job instance id")
    private Long jobInstanceId;

    @NotNull
    @Schema(description = "Job dispatch version")
    private Long dispatchVersion;

    @NotNull
    @Schema(description = "Circle id")
    private Long circleId;

    @NotBlank
    @Schema(description = "Task id")
    private String taskId;
}
