package io.distributedjobscheduler.server.admin.request.server;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author inhere
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "JobSlotRequest", description = "Job slot list request")
public class JobSlotRequest extends PageRequest {
}

