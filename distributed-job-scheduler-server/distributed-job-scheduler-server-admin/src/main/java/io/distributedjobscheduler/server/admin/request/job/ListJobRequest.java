package io.distributedjobscheduler.server.admin.request.job;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * @author zhenghongyang sakuraovq@gmail.com
 * @since 1.0.0
 */
@Data
@Schema
@EqualsAndHashCode(callSuper = true)
public class ListJobRequest extends PageRequest {
    @Schema(description = "NamespaceId")
    @NotNull
    private Long namespaceId;

    @Schema(description = "App primary id", required = true)
    private Long appId;

    @Schema(description = "Job name", required = true)
    private String name;

    @Schema(description = "Job status", required = true)
    private Integer status;
}
