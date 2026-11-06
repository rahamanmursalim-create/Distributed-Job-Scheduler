package io.distributedjobscheduler.server.admin.request.namespace;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
@Schema
@EqualsAndHashCode(callSuper = true)
public class ListNamespaceRequest extends PageRequest {
    @Schema(description = "Search name.")
    private String name;
}
