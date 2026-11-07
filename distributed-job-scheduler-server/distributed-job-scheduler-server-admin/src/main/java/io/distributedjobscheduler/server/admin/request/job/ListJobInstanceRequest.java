package io.distributedjobscheduler.server.admin.request.job;

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
@Schema()
public class ListJobInstanceRequest extends PageRequest {

    @Schema(description = "Instance id")
    private Long id;

    @Schema(description = "App primary id")
    private Long appId;

    @Schema(description = "Namespace primary id")
    private Long namespaceId;

    @Schema(description = "Job id")
    private Long jobId;

    @Schema(description = "Job instance status")
    private Integer status;

    @Schema(description = "Job begin time.")
    private Long beginTime;

    @Schema(description = "Job end time.")
    private Long endTime;
}
