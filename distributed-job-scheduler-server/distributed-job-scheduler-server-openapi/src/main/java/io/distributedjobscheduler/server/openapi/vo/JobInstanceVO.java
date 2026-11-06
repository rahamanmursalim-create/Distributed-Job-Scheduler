package io.distributedjobscheduler.server.openapi.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhenghongyang sakuraovq@gmail.com
 * @since 1.0.0
 */
@Data
public class JobInstanceVO {

    @Schema(description = "Job Name", required = true)
    private String jobName;

    @Schema(description = "Data")
    private String data;
}
