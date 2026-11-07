package io.distributedjobscheduler.server.admin.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Schema(name = "AdminSystemVO", description = "Admin System query VO")
public class SystemVO {


    @Schema(description = "PK")
    private Integer id;

    @Schema(description = "version")
    private String version;

    @Schema(description = "cluster version")
    private Long clusterVersion;

    @Schema(description = "cluster delay version")
    private Long clusterDelayVersion;

    @Schema(description = "worker supervisor slot")
    private Integer workerSupervisorSlot;

    @Schema(description = "delay zset slot")
    private Integer delayZsetSlot;

    @Schema(description = "delay fail zset slot")
    private Integer delayFailZsetSlot;

    @Schema(description = "delay add list slot")
    private Integer delayAddListSlot;

    @Schema(description = "delay status list slot")
    private Integer delayStatusListSlot;

    @Schema(description = "delay delete list slot")
    private Integer delayDeleteListSlot;

    @Schema(description = "job keep days")
    private Integer jobKeepDays;

    @Schema(description = "delay keep days")
    private Integer delayKeepDays;

    @Schema(description = " sever keep days")
    private Integer serverKeepDays;

    @Schema(description = "worker keep days")
    private Integer workerKeepDays;

    @Schema(description = "max slot")
    private Integer maxSlot;
}
