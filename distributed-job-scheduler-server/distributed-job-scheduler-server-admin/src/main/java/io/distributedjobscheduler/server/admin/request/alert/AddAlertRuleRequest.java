package io.distributedjobscheduler.server.admin.request.alert;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.5
 */
@Data
@Schema
public class AddAlertRuleRequest {
    @NotBlank
    @Schema(description = "Name", required = true)
    private String name;

    @NotNull
    @Schema(description = "Name", required = true)
    private List<Integer> namespaceAppIds;

    @NotNull
    @Schema(description = "Events")
    private List<String> events;

    @NotNull
    @Schema(description = "metrics")
    private List<AlertRuleMetricsRequest> metrics;

    @NotBlank
    @Schema(description = "locale")
    private String locale;

    @NotBlank
    @Schema(description = "Method", required = true)
    private String method;

    @NotBlank
    @Schema(description = "Url", required = true)
    private String url;

    @Schema(description = "Secret")
    private String secret;

    @NotNull
    @Schema(description = "Status", required = true)
    private Integer status;

    protected static class AlertRuleMetricsRequest {

    }
}
