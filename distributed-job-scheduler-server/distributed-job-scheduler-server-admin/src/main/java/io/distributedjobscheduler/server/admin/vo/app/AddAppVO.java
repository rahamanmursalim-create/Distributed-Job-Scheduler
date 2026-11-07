package io.distributedjobscheduler.server.admin.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class AddAppVO {

    @Schema(description = "New app primary id", required = true)
    private Long id;
}
