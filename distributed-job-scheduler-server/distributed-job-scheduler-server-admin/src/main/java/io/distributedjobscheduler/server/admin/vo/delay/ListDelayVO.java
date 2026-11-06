package io.distributedjobscheduler.server.admin.vo.delay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class ListDelayVO {
    @Schema(description = "Delay primary id", required = true)
    private Long id;

    @Schema(description = "Delay pid", required = true)
    private Long pid;

    @Schema(description = "Delay cid", required = true)
    private Long cid;

    @Schema(description = "Namespace id", required = true)
    private Long namespaceId;

    @Schema(description = "Application id", required = true)
    private Long appId;

    @Schema(description = "Application name", required = true)
    private String appName;

    @Schema(description = "Name", required = true)
    private String name;

    @Schema(description = "Topic", required = true)
    private String topic;

    @Schema(description = "Total", required = true)
    private Long total;

    @Schema(description = "Ready", required = true)
    private Long ready;

    @Schema(description = "Fail count", required = true)
    private Long failCount;

    @Schema(description = "Description", required = true)
    private String description;

    @Schema(description = "Processor Info", required = true)
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

    @Schema(description = "Create time", required = true)
    private Long createTime;

    @Schema(description = "Fail topic enable", required = true)
    private Integer failTopicEnable;

    @Schema(description = "Fail topic concurrency", required = true)
    private Integer failTopicConcurrency;
}
