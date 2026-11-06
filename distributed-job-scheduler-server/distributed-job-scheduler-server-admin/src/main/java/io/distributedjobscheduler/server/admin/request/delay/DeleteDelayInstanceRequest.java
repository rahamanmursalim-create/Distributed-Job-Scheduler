package io.distributedjobscheduler.server.admin.request.delay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class DeleteDelayInstanceRequest {
    @Schema(description = "Delay task id", required = true)
    private String taskId;
}
