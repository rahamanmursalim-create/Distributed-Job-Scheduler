package io.distributedjobscheduler.server.admin.request.namespace;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Schema()
@Data
public class UpdateNamespaceRequest {

    @NotNull
    @Schema(description = "Namespace primary id", required = true)
    private Long id;

    @NotBlank
    @Schema(description = "Namespace name", required = true)
    private String name;
}
