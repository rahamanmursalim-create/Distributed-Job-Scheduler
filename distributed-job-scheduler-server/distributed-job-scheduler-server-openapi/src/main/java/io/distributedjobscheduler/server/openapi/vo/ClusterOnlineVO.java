package io.distributedjobscheduler.server.openapi.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.3
 */
@Data
public class ClusterOnlineVO {
    private List<String> servers;
}
