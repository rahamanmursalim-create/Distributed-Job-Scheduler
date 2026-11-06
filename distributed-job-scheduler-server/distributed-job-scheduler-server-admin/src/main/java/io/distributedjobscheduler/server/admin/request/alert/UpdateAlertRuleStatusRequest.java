package io.distributedjobscheduler.server.admin.request.alert;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.5
 */
@Data
@Schema
public class UpdateAlertRuleStatusRequest {

    @NotNull
    @Schema(description = "Id", required = true)
    private Long id;

    @NotNull
    @Schema(description = "status", required = true)
    private Integer status;
}
