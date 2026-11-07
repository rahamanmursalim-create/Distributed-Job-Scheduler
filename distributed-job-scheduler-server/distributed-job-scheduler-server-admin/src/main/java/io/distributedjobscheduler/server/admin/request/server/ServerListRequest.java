package io.distributedjobscheduler.server.admin.request.server;

import io.distributedjobscheduler.server.admin.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ServerListRequest", description = "ServerList page list request")
public class ServerListRequest extends PageRequest {
    @Schema(description = "Server address")
    private String address;
}

