package io.distributedjobscheduler.server.admin.vo.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhenghongyang sakuraovq@gmail.com
 * @since 1.0.0
 */
@Data
public class ListJobVO {

    @Schema(description = "Primary id", required = true)
    private Long id;

    @Schema(description = "Namespace primary id")
    private Long namespaceId;

    @Schema(description = "Workflow_id")
    private Long workflowId;

    @Schema(description = "App primary id")
    private Long appId;

    @Schema(description = "App name")
    private String appName;

    @Schema(description = "Job name")
    private String name;

    @Schema(description = "Job desc")
    private String description;

    @Schema(description = "Job process type: bean/shell/python, default(bean)")
    private String processorType;

    @Schema(description = "Job process info")
    private String processorInfo;

    @Schema(description = "Shell processor type")
    private String shellProcessorType;

    @Schema(description = "Shell processor info")
    private String shellProcessorInfo;

    @Schema(description = "Kettle processor type")
    private String kettleProcessorType;

    @Schema(description = "Kettle processor info")
    private String kettleProcessorInfo;

    @Schema(description = "Sharding params")
    private String shardingParams;

    @Schema(description = "Execute type: standalone, broadcast, MR")
    private String executeType;

    @Schema(description = "Job params type")
    private String paramsType;

    @Schema(description = "Job params")
    private String params;

    @Schema(description = "Job extend params type")
    private String extendParamsType;

    @Schema(description = "Job extend params")
    private String extendParams;

    @Schema(description = "Job execute fail retry times")
    private Integer failRetryTimes;

    @Schema(description = "Job execute fail retry interval")
    private Integer failRetryInterval;

    @Schema(description = "Job execute timeout", required = true)
    private Integer executeTimeout;

    @Schema(description = "Job execute concurrency, default(1)")
    private Integer concurrency;

    @Schema(description = "Job type cron/second/delay, default(cron)")
    private String timeExpressionType;

    @Schema(description = "Job type=cron need, cron express")
    private String timeExpression;

    @Schema(description = "Job express value(secondDelay/fixedRate/oneTime)")
    private Long timeExpressionValue;

    @Schema(description = "Job execute strategy")
    private Integer executeStrategy;

    @Schema(description = "Job status 1=running 2=stop, default(1)")
    private Integer status;

    @Schema(description = "Create time")
    private Long createTime;

    @Schema(description = "Update time")
    private Long updateTime;
}
