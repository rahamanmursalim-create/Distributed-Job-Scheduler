package io.distributedjobscheduler.server.admin.request.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author rikiy
 * @since 1.0.0
 */
@Data
@Schema(name = "AdminSystemUpdateRequest", description = "AdminSystem Update  Request")
public class SystemUpdateRequest {
    @NotNull
    @Schema(description = "delay zset slot")
    private Integer delayZsetSlot;

    @NotNull
    @Schema(description = "delay fail zset slot")
    private Integer delayFailZsetSlot;

    @NotNull
    @Schema(description = "delay add list slot")
    private Integer delayAddListSlot;

    @NotNull
    @Schema(description = "delay status list slot")
    private Integer delayStatusListSlot;

    @NotNull
    @Schema(description = "delay delete list slot")
    private Integer delayDeleteListSlot;

    @NotNull
    @Schema(description = "max slot")
    private Integer maxSlot;

    @NotNull
    @Schema(description = "job keep days")
    private Integer jobKeepDays;

    @NotNull
    @Schema(description = "delay keep days")
    private Integer delayKeepDays;

    @NotNull
    @Schema(description = " sever keep days")
    private Integer serverKeepDays;

    @NotNull
    @Schema(description = "worker keep days")
    private Integer workerKeepDays;
}

