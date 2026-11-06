package io.distributedjobscheduler.server.openapi.request;

import io.distributedjobscheduler.common.constant.FailStatusEnum;
import io.distributedjobscheduler.common.constant.InstanceStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author zhenghongyang sakuraovq@gmail.com
 * @since 1.0.0
 */
@Data
public class WorkerJobInstanceStatusRequest {

    @NotNull
    @Schema(description = "Job id.")
    private Long jobId;

    @NotNull
    @Schema(description = "Job instance id.")
    private Long jobInstanceId;

    @Schema(description = "Current circleId. Only for second delay task.")
    private Long circleId = 0L;

    /**
     * @see InstanceStatusEnum
     */
    @NotNull
    @Schema(description = "Job instance status.")
    private Integer status;

    /**
     * @see FailStatusEnum#getStatus()
     */
    @NotNull
    @Schema(description = "Fail status")
    private Integer failStatus;

    @NotBlank
    @Schema(description = "Result")
    private String result;
}
