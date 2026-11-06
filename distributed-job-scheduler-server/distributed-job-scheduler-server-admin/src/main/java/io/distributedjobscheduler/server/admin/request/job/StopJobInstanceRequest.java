package io.distributedjobscheduler.server.admin.request.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
@Schema()
public class StopJobInstanceRequest {
    @NotNull
    @Schema(description = "Stop job instance id", required = true)
    private Long id;
}
