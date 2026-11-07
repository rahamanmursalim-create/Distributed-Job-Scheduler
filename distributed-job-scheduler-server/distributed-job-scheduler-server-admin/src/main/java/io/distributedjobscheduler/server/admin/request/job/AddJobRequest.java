package io.distributedjobscheduler.server.admin.request.job;

import io.distributedjobscheduler.common.constant.HttpMethodEnum;
import io.distributedjobscheduler.common.constant.MediaTypeEnum;
import io.distributedjobscheduler.common.constant.RequestTypeEnum;
import io.distributedjobscheduler.common.constant.ResponseModeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Schema
public class AddJobRequest {
    @NotNull
    @Schema(description = "Namespace primary id", required = true)
    private Long namespaceId;

    @NotNull
    @Schema(description = "App primary id", required = true)
    private Long appId;

    @NotBlank
    @Schema(description = "Job name", required = true)
    private String name;

    @Schema(description = "Job desc")
    private String description;

    @NotBlank
    @Schema(description = "Job process type: bean/shell/python, default(bean)", required = true)
    private String processorType;

    @Schema(description = "Job process info")
    private String processorInfo;

    @Schema(description = "Http processor")
    private HttpProcessorRequest httpProcessor;

    @Schema(description = "Shell processor info")
    private String shellProcessorInfo;

    @Schema(description = "Shell processor type")
    private String shellProcessorType;

    @Schema(description = "Kettle processor type")
    private String kettleProcessorType;

    @Schema(description = "Kettle processor info")
    private String kettleProcessorInfo;

    @Schema(description = "Sharding params")
    private String shardingParams;

    @NotBlank
    @Schema(description = "Execute type: standalone, broadcast, MR", required = true)
    private String executeType;

    @Schema(description = "Job params type")
    private String paramsType;

    @Schema(description = "Job params")
    private String params;

    @Schema(description = "Job extend params type")
    private String extendParamsType;

    @Schema(description = "Job extend params")
    private String extendParams;

    @NotNull
    @Schema(description = "Job execute fail retry times", required = true)
    private Integer failRetryTimes;

    @NotNull
    @Schema(description = "Job execute fail retry interval", required = true)
    private Integer failRetryInterval;

    @NotNull
    @Schema(description = "Job execute timeout", required = true)
    private Integer executeTimeout;

    @NotNull
    @Schema(description = "Job execute concurrency, default(1)", required = true)
    private Integer concurrency;

    @NotNull
    @Schema(description = "Job type cron/second/delay, default(cron)", required = true)
    private String timeExpressionType;

    @Schema(description = "Job cron express")
    private String timeExpression;

    @Schema(description = "Job express value(secondDelay/fixedRate/oneTime)")
    private Long timeExpressionValue;

    @NotNull
    @Schema(description = "Job execute strategy", required = true)
    private Integer executeStrategy;

    @NotNull
    @Schema(description = "Job status 1=running 2=stop, default(1)", required = true)
    private Integer status;

    @Data
    public static class HttpProcessorRequest {
        @Schema(description = "Http url")
        private String url;

        @Schema(description = "Http method")
        private String method;

        @Schema(description = "Timeout(ms)")
        private Long timeout;

        @Schema(description = "Content Type")
        private String contentType;

        @Schema(description = "Body")
        private String body;

        @Schema(description = "Cookie")
        private String cookie;

        @Schema(description = "Response mode")
        private String responseMode;

        @Schema(description = "JSON => key")
        private String key;

        @Schema(description = "JSON => status,value or string")
        private String value;

        @Schema(description = "Request type")
        private String requestType;
    }
}
