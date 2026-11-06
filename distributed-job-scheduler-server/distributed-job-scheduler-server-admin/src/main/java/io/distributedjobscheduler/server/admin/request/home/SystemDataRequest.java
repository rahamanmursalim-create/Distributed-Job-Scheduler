package io.distributedjobscheduler.server.admin.request.home;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.2
 */
@Data
public class SystemDataRequest {
    @NotNull
    @Schema(description = "App namespace id", required = true)
    private Long namespaceId;
}
