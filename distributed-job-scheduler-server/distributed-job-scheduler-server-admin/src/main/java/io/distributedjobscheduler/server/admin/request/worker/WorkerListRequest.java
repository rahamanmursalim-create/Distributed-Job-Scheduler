package io.distributedjobscheduler.server.admin.request.worker;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author riki
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "WorkerListRequest", description = "WorkerList page list request")
public class WorkerListRequest extends PageRequest {
    @Schema(description = "Appid")
    private Long appId;

    @Schema(description = "NamespaceId")
    private Long namespaceId;

    @Schema(description = "Address")
    private String address;
}
