package io.distributedjobscheduler.server.admin.request.task;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.7
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ListTaskRequest extends PageRequest {
    @NotNull
    @Schema(description = "Job instance id", required = true)
    private Long jobInstanceId;

    @NotBlank
    @Schema(description = "Time expression type", required = true)
    private String timeExpressionType;
}
