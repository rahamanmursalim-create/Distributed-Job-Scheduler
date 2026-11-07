package io.distributedjobscheduler.worker.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.3
 */
@Data
public class ClusterDTO {
    private List<String> servers;
}
