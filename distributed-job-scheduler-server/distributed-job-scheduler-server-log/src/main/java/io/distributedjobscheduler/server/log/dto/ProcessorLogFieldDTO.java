package io.distributedjobscheduler.server.log.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ProcessorLogFieldDTO {
    private String name;
    private String value;

    public ProcessorLogFieldDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
