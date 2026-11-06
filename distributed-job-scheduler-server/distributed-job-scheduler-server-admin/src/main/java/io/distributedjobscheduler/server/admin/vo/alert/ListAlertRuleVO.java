package io.distributedjobscheduler.server.admin.vo.alert;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.5
 */
@Data
@Schema
public class ListAlertRuleVO {

    @Schema(description = "ID", required = true)
    private Long id;

    @Schema(description = "Name", required = true)
    private String name;

    @Schema(description = "Name", required = true)
    private List<Long> namespaceAppIds;

    @Schema(description = "Events")
    private List<String> events;

    @Schema(description = "metrics")
    private List<AlertRuleMetricsVO> metrics;

    @Schema(description = "locale")
    private String locale;

    @Schema(description = "Method", required = true)
    private String method;

    @Schema(description = "Url", required = true)
    private String url;

    @Schema(description = "Secret")
    private String secret;

    @Schema(description = "Status", required = true)
    private Integer status;

    @Schema(description = "Update time", required = true)
    private Long updateTime;

    @Schema(description = "Create time", required = true)
    private Long createTime;

    @Data
    public static class AlertRuleMetricsVO {

    }
}
