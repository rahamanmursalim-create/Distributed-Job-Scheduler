package io.distributedjobscheduler.server.admin.request.delay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class UpdateDelayRequest {
    @NotNull
    @Schema(description = "Delay primary id", required = true)
    private Long id;

    @NotNull
    @Schema(description = "Delay pid", required = true)
    private Long pid;

    @NotNull
    @Schema(description = "Delay cid", required = true)
    private Long cid;

    @Schema(description = "Namespace id", required = true)
    private Long namespaceId;

    @Schema(description = "Application id", required = true)
    private Long appId;

    @Schema(description = "Name", required = true)
    private String name;

    @Schema(description = "Topic", required = true)
    private String topic;

    @Schema(description = "Description", required = true)
    private String description;

    @Schema(description = "Processor info", required = true)
    private String processorInfo;

    @Schema(description = "Fail retry times", required = true)
    private Integer failRetryTimes;

    @Schema(description = "fail retry interval", required = true)
    private Integer failRetryInterval;

    @Schema(description = "Execute timeout", required = true)
    private Integer executeTimeout;

    @Schema(description = "Concurrency", required = true)
    private Integer concurrency;

    @Schema(description = "Blocking size", required = true)
    private Integer blockingSize;

    @NotNull
    @Schema(description = "Fail topic enable", required = true)
    private Integer failTopicEnable;

    @Schema(description = "Fail topic concurrency", required = true)
    private Integer failTopicConcurrency;
}
