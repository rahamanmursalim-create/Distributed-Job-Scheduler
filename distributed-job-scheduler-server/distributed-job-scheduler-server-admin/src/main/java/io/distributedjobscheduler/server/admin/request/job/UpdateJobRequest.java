package io.distributedjobscheduler.server.admin.request.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema
public class UpdateJobRequest extends AddJobRequest {

    @NotNull
    @Schema(description = "Job primary id", required = true)
    private Long id;
}
