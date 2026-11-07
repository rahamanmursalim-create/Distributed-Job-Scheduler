package io.distributedjobscheduler.server.repository.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.2
 */
@Data
public class GroupCountDTO {
    private Integer groupBy;
    private Long count;

    public GroupCountDTO(Integer groupBy, Long count) {
        this.groupBy = groupBy;
        this.count = count;
    }
}
