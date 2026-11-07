package io.distributedjobscheduler.server.admin.request.delay;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ListDelayRequest extends PageRequest {
    @Schema(description = "Namespace id", required = true)
    private Long namespaceId;

    @Schema(description = "Application id", required = true)
    private Long appId;

    @Schema(description = "Name", required = true)
    private String name;

    @Schema(description = "Topic", required = true)
    private String topic;
}
