package io.distributedjobscheduler.server.openapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.8
 */
@Data
public class WorkerJobInstanceTaskBatchRequest {

    @Schema(description = "Job instance task list.Aggregation many circle task, if second delay task.")
    private List<WorkerJobInstanceTaskRequest> taskRequestList;

    @Data
    public static class WorkerJobInstanceTaskRequest implements Serializable {

        @NotNull
        @Schema(description = "Job id.")
        private Long jobId;

        @NotNull
        @Schema(description = "Job instance id.")
        private Long jobInstanceId;

        @NotNull
        @Schema(description = "Dispatch version")
        private Long dispatchVersion;

        @NotNull
        @Schema(description = "Only for second delay task.")
        private Long circleId;

        @NotBlank
        @Schema(description = "Task unique id.")
        private String taskId;

        @NotBlank
        @Schema(description = "Task parent unique id.")
        private String parentTaskId;

        @NotBlank
        @Schema(description = "Task name.")
        private String taskName;

        @NotNull
        @Schema(description = "Task status.")
        private Integer status;

        @NotBlank
        @Schema(description = "Task result.")
        private String result;

        @NotBlank
        @Schema(description = "Task worker address")
        private String workerAddress;

        @NotNull
        @Schema(description = "Task create time.")
        private Long createTime;

        @NotNull
        @Schema(description = "Task update time.")
        private Long updateTime;
    }
}
