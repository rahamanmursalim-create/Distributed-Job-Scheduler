package io.distributedjobscheduler.server.openapi.request;

import io.distributedjobscheduler.common.constant.ProtocolTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerStartRequest {

    @NotBlank
    @Schema(description = "Worker unique id.")
    private String workerKey;

    @NotBlank
    @Schema(description = "App name")
    private String appName;

    @NotBlank
    @Pattern(regexp = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}$", message = "must be in format 'IP:PORT'")
    @Schema(description = "Worker address")
    private String address;

    @NotBlank
    @Schema(description = "Worker agent version")
    private String version;

    @Schema(description = "Worker current protocol type, http is default.")
    private String protocolType = ProtocolTypeEnum.HTTP.getType();

    @Schema(description = "metric")
    private Metric metric;

    @Data
    public static class Metric {

    }
}
