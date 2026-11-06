package io.distributedjobscheduler.server.admin.vo.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class TimeExpressionVO {
    @Schema(description = "Valid status", required = true)
    private Integer valid;

    @Schema(description = "Time list", required = true)
    private List<Long> list;
}
