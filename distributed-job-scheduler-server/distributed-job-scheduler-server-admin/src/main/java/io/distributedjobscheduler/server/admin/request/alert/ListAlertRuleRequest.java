package io.distributedjobscheduler.server.admin.request.alert;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.5
 */
@Data
@Schema
@EqualsAndHashCode(callSuper = true)
public class ListAlertRuleRequest extends PageRequest {

    @Schema(description = "Name", required = true)
    private String name;
}
