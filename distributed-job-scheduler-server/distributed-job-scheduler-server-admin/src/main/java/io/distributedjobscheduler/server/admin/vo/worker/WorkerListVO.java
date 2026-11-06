package io.distributedjobscheduler.server.admin.vo.worker;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author riki
 * @since 1.0.0
 */
@Data
@Schema(name = "WorkerListVO", description = "Admin Worker list VO")
public class WorkerListVO {

    @Schema(description = "pk")
    private Long id;

    @Schema(description = "Appid")
    private Long appId;

    @Schema(description = "NamespaceId")
    private Long namespaceId;

    @Schema(description = "App name")
    private String appName;

    @Schema(description = "Worker key")
    private String workerKey;

    @Schema(description = "Slot id.")
    private Long slotsId;

    @Schema(description = "Address")
    private String address;

    @Schema(description = "Protocol type.")
    private String protocolType;

    @Schema(description = "Version")
    private String version;

    @Schema(description = "Last heartbeat time")
    private Long lastHeartbeatTime;

    @Schema(description = "Status")
    private Integer status;

    @Schema(description = "Metric")
    private String metric;

    @Schema(description = "Worker create time")
    private Long createTime;

    @Schema(description = "Worker update time")
    private Long updateTime;
}
