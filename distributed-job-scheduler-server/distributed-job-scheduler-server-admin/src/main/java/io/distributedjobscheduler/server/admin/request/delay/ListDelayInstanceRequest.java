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
public class ListDelayInstanceRequest extends PageRequest {
    @Schema(description = "Namespace id", required = true)
    private Long namespaceId;

    @Schema(description = "Application id", required = true)
    private Long appId;

    @Schema(description = "Delay id", required = true)
    private Long delayId;

    @Schema(description = "Status", required = true)
    private Integer status;

    @Schema(description = "Task id", required = true)
    private String taskId;

    @Schema(description = "Job begin time.")
    private Long beginTime;

    @Schema(description = "Job end time.")
    private Long endTime;
}
