package io.distributedjobscheduler.server.admin.request.app;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
@Schema
@EqualsAndHashCode(callSuper = true)
public class ListAppRequest extends PageRequest {
    @NotNull
    @Schema(description = "App namespace id", required = true)
    private Long namespaceId;

    @Schema(description = "Search name.")
    private String name;
}
