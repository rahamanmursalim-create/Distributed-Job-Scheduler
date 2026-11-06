package io.distributedjobscheduler.server.admin.request.delay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
@Schema
public class AddDelayRequest {
    @NotNull
    @Schema(description = "Namespace id", required = true)
    private Long namespaceId;

    @NotNull
    @Schema(description = "Application id", required = true)
    private Long appId;

    @NotBlank
    @Schema(description = "Name", required = true)
    private String name;

    @NotBlank
    @Schema(description = "Topic", required = true)
    private String topic;

    @Schema(description = "Description", required = true)
    private String description;

    @NotBlank
    @Schema(description = "Processor", required = true)
    private String processorInfo;

    @NotNull
    @Schema(description = "Fail retry times", required = true)
    private Integer failRetryTimes;

    @NotNull
    @Schema(description = "fail retry interval", required = true)
    private Integer failRetryInterval;

    @NotNull
    @Schema(description = "Execute timeout", required = true)
    private Integer executeTimeout;

    @NotNull
    @Schema(description = "Concurrency", required = true)
    private Integer concurrency;

    @NotNull
    @Schema(description = "Blocking size", required = true)
    private Integer blockingSize;

    @NotNull
    @Schema(description = "Fail topic enable", required = true)
    private Integer failTopicEnable;

    @NotNull
    @Schema(description = "Fail topic concurrency", required = true)
    private Integer failTopicConcurrency;
}
