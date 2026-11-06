package io.distributedjobscheduler.server.admin.vo.server;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author riki
 * @since 1.0.0
 */
@Data
@Schema(name = "ServerListVO", description = "server list VO")
public class ServerListVO {
    @Schema(description = "pk")
    private Long id;

    @Schema(description = "Server IP")
    private String ip;

    @Schema(description = "Server address")
    private String akkaAddress;

    @Schema(description = "Server status")
    private Integer status;

    @Schema(description = "Server create time")
    private Long createTime;
}
