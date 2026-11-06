package io.distributedjobscheduler.server.admin.vo.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class ListJobInstanceVO {
    @Schema(description = "Job instance primary id")
    private Long id;

    @Schema(description = "Namespace primary id")
    private Long namespaceId;

    @Schema(description = "App primary id")
    private Long appId;

    @Schema(description = "App name")
    private String appName;

    @Schema(description = "Job id")
    private Long jobId;

    @Schema(description = "Job name")
    private String jobName;

    @Schema(description = "Job name")
    private String name;

    @Schema(description = "Job desc")
    private String description;

    @Schema(description = "Job process type: bean/shell/python, default(bean)")
    private String processorType;

    @Schema(description = "Job process info")
    private String processorInfo;

    @Schema(description = "Execute type: standalone, broadcast, MR")
    private String executeType;

    @Schema(description = "Dispatch version")
    private Long dispatchVersion;

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

    @Schema(description = "Job execute concurrency, default(1)")
    private Integer concurrency;

    @Schema(description = "Job type cron/second/delay, default(cron)")
    private String timeExpressionType;

    @Schema(description = "Job type=cron need, cron express")
    private String timeExpression;

    @Schema(description = "Job execute strategy")
    private Integer executeStrategy;

    @Schema(description = "Execute once")
    private Integer executeOnce;

    @Schema(description = "Job status 1=running 2=stop, default(1)")
    private Integer status;

    @Schema(description = "Job fail status")
    private Integer failStatus;

    @Schema(description = "Worker address")
    private String workerAddress;

    @Schema(description = "Job execute complete time")
    private Long completeTime;

    @Schema(description = "Job execute time")
    private Long executeTime;

    @Schema(description = "Job execute last report time")
    private Long lastReportTime;

    @Schema(description = "Job  create time")
    private Long createTime;
}
