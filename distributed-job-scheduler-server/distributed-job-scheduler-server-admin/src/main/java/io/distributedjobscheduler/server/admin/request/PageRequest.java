package io.distributedjobscheduler.server.admin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Schema(name = "BasePageRequest", description = "Base Page Request")
public class PageRequest {
    @Schema(description = "List page.")
    @NotNull
    @Min(1)
    private Integer page = 1;

    @Schema(description = "List size. default 10")
    @NotNull
    @Min(1)
    private Integer size = 10;
}

