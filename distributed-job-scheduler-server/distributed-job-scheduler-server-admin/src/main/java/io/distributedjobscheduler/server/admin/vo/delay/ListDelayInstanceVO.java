package io.distributedjobscheduler.server.admin.vo.delay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class ListDelayInstanceVO {

    @Schema(description = "ID", required = true)
    private Long id;

    @Schema(description = "Namespace", required = true)
    private Long namespaceId;

    @Schema(description = "Application name", required = true)
    private String appName;

    @Schema(description = "Application", required = true)
    private Long appId;

    @Schema(description = "Task id", required = true)
    private String taskId;

    @Schema(description = "Topic", required = true)
    private String topic;

    @Schema(description = "Delay id", required = true)
    private Long delayId;

    @Schema(description = "Delay name", required = true)
    private String delayName;

    @Schema(description = "Delay params", required = true)
    private String delayParams;

    @Schema(description = "Delay extra", required = true)
    private String delayExtra;

    @Schema(description = "Status", required = true)
    private Integer status;

    @Schema(description = "Fail status", required = true)
    private Integer failStatus;

    @Schema(description = "Execute time", required = true)
    private Long executeTime;

    @Schema(description = "Complete time", required = true)
    private Long completeTime;

    @Schema(description = "Worker address", required = true)
    private String workerAddress;

    @Schema(description = "Create time", required = true)
    private Long createTime;

}
