package io.distributedjobscheduler.server.admin.request.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class TimeExpressionRequest {
    @NotBlank
    @Schema(description = "Expression", required = true)
    private String timeExpression;
}
