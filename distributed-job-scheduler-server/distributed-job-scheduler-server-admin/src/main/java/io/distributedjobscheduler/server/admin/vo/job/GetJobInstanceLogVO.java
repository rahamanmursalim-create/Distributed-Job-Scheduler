package io.distributedjobscheduler.server.admin.vo.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Schema()
public class GetJobInstanceLogVO {
    @Schema(description = "Job id", required = true)
    private Long jobId;

    @Schema(description = "Job instance id", required = true)
    private Long jobInstanceId;

    @Schema(description = "Job message", required = true)
    private String message;
}
