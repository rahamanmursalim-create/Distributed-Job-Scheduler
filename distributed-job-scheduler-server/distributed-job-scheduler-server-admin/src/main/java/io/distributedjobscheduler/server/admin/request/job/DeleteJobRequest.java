package io.distributedjobscheduler.server.admin.request.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author zhenghongyang sakuraovq@gmail.com
 * @since 1.0.0
 */
@Data
public class DeleteJobRequest {
    @NotNull
    @Schema(description = "Delete job id", required = true)
    private Long id;
}
