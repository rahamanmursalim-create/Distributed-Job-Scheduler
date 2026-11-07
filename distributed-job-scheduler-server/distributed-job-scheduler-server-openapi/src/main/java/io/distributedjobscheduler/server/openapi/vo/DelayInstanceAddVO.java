package io.distributedjobscheduler.server.openapi.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class DelayInstanceAddVO {

    @Schema(description = "Delay task unique id. If is null or blank, will to auto generate.")
    private String taskId;
}
