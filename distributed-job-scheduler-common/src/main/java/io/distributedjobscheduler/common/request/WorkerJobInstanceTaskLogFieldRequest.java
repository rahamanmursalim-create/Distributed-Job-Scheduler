package io.distributedjobscheduler.common.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerJobInstanceTaskLogFieldRequest implements Serializable {
    private String name;
    private String value;

    /**
     * Non arg constructor for Serializable.
     */
    @SuppressWarnings("unused")
    public WorkerJobInstanceTaskLogFieldRequest() {

    }

    public WorkerJobInstanceTaskLogFieldRequest(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
