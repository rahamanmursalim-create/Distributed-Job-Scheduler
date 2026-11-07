package io.distributedjobscheduler.server.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class PageVO<T> {
    @Schema(description = "Current page.")
    private Integer page;

    @Schema(description = "Page size.")
    private Integer size;

    @Schema(description = "Page total.")
    private Long total;

    @Schema(description = "Data list.")
    private List<T> list;
}
