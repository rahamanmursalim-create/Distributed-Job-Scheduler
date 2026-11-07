package io.distributedjobscheduler.server.admin.vo.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Data
public class ListTaskVO {
    @Schema(description = "Pk id")
    private Long id;

    @Schema(description = "Job id")
    private Long jobId;

    @Schema(description = "Instance id")
    private Long jobInstanceId;

    @Schema(description = "Circle id")
    private Long circleId;

    @Schema(description = "Dispatch version")
    private Long dispatchVersion;

    @Schema(description = "Parent task id")
    private String parentTaskId;

    @Schema(description = "Task id")
    private String taskId;

    @Schema(description = "Task name")
    private String taskName;

    @Schema(description = "Status")
    private Integer status;

    @Schema(description = "Result")
    private String result;

    @Schema(description = "Child count")
    private Long childCount;

    @Schema(description = "Pull")
    private Integer pull;

    @Schema(description = "Worker address")
    private String workerAddress;

    @Schema(description = "Create time")
    private Long createTime;

    @Schema(description = "Update time")
    private Long updateTime;
}
