package io.distributedjobscheduler.server.admin.request.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class DeleteJobInstanceRequest {
    @NotNull
    @Schema(description = "Delete id", required = true)
    private Long id;
}
