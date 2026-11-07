package io.distributedjobscheduler.server.admin.vo.server;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.persistence.Column;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Schema(name = "ServerListVO", description = "server list VO")
public class JobSlotListVO {
    @Schema(description = "pk")
    private Long id;

    @Schema(description = "Server id")
    private Long serverId;

    @Schema(description = "Server address")
    private String akkaAddress;

    @Schema(description = "Server status")
    private Integer serverStatus;

    @Schema(description = "Server create time")
    private Long createTime;

    @Schema(description = "Server update time")
    private Long updateTime;
}
