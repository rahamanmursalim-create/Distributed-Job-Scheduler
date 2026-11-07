package io.distributedjobscheduler.server.admin.request.part;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 */
@Data
public class MenuMetaRequest {
    @Schema(description = "icon name")
    private String icon;

    @Schema(description = "I18n title name")
    private String title;
}
