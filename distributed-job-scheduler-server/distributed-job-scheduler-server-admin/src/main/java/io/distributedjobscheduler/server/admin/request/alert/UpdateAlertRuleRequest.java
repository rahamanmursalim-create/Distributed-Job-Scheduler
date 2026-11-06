package io.distributedjobscheduler.server.admin.request.alert;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.5
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema
public class UpdateAlertRuleRequest extends AddAlertRuleRequest {

    @NotNull
    @Schema(description = "Id", required = true)
    private Long id;
}
