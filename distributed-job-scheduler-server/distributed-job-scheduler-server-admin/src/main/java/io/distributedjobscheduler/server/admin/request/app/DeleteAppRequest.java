package io.distributedjobscheduler.server.admin.request.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class DeleteAppRequest {
    @NotNull
    @Schema(description = "App primary id", required = true)
    private Long id;
}
