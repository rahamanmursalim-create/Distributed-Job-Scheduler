package io.distributedjobscheduler.server.openapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerHeartbeatRequest {

    @NotNull
    @Schema(description = "App id")
    private Long appId;

    @NotBlank
    @Schema(description = "App name")
    private String appName;

    @NotBlank
    @Pattern(regexp = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}$", message = "must be in format 'IP:PORT'")
    @Schema(description = "Worker address.")
    private String address;

    @NotBlank
    @Schema(description = "Worker version")
    private String version;

    @NotNull
    @Schema(description = " Running job instance ids.")
    private List<Long> runningJobInstanceIds;
}
