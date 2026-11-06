package io.distributedjobscheduler.server.openapi.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

/**
 * @author zhenghongyang sakuraovq@gmail.com
 * @since 1.0.0
 */
@Data
public class ServerWorkerStartVO {

    @Schema(description = "App id", required = true)
    private Long appId;

    @Schema(description = "App name", required = true)
    private String appName;

    @Schema(description = "Online workers  and exclude start worker.", required = true)
    private Set<String> workerAddressList;
}
