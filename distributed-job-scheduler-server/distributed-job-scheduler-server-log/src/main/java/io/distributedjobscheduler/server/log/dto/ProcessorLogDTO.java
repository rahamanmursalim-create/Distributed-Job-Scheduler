package io.distributedjobscheduler.server.log.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ProcessorLogDTO {
    private String taskId;
    private String workerAddress;
    private List<ProcessorLogFieldDTO> fields;
    private Long time;
}
