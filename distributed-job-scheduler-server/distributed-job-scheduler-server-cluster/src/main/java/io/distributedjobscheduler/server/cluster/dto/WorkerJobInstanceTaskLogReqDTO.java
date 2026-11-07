package io.distributedjobscheduler.server.cluster.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
@Data
public class WorkerJobInstanceTaskLogReqDTO {
    private List<List<WorkerJobInstanceTaskLogFieldReqDTO>> fieldList;

    @Data
    public static class WorkerJobInstanceTaskLogFieldReqDTO {
        private String name;
        private String value;
    }
}
