package io.distributedjobscheduler.server.admin.request.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Schema()
@Data
public class UpdateAppRequest {

    @NotNull
    @Schema(description = "App primary id", required = true)
    private Long id;

    @NotNull
    @Schema(description = "App namespace id", required = true)
    private Long namespaceId;

    @NotBlank
    @Schema(description = "App name", required = true)
    private String name;

    @Schema(description = "App desc", required = true)
    private String desc = "";
}
