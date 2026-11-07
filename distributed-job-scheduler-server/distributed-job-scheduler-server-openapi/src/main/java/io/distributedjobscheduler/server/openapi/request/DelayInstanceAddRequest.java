package io.distributedjobscheduler.server.openapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class DelayInstanceAddRequest {

    @Schema(description = "Delay task unique id. If is null or blank, will to auto generate.")
    private String taskId;

    @NotBlank
    @Schema(description = "topic")
    private String topic;

    @NotBlank
    @Schema(description = "Delay task params.")
    private String params;

    @Schema(description = "Delay task extra params.")
    private String extra;

    @NotNull
    @Schema(description = "Delay task execute timestamp.")
    private Long executeTime;
}
