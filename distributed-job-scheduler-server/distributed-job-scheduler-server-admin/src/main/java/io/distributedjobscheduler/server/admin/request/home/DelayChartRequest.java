package io.distributedjobscheduler.server.admin.request.home;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.2
 */
@Data
public class DelayChartRequest {
    @NotNull
    @Schema(description = "App namespace id", required = true)
    private Long namespaceId;

    @NotNull
    @Schema(description = "Job begin time.")
    private Long beginTime;

    @NotNull
    @Schema(description = "Job end time.")
    private Long endTime;
}
