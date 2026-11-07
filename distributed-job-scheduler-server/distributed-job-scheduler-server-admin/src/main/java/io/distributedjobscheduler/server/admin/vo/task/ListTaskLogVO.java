package io.distributedjobscheduler.server.admin.vo.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Data
public class ListTaskLogVO {
    @Schema(description = "List")
    private List<String> list;

    @Schema(description = "Time")
    private Long time;

    @Schema(description = "Load complete")
    private Integer complete;
}
