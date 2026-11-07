package io.distributedjobscheduler.server.admin.vo.delay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class DeleteDelayInstanceVO {
    @NotNull
    @Schema(description = "Delete job id", required = true)
    private Long id;
}
