package io.distributedjobscheduler.common.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerJobInstanceTaskLogRequest implements Serializable {
    private List<List<WorkerJobInstanceTaskLogFieldRequest>> fieldList;
}
