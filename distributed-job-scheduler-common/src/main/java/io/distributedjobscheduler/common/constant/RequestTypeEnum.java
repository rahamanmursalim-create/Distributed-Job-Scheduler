package io.distributedjobscheduler.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Getter
@AllArgsConstructor
public enum RequestTypeEnum {
    /**
     * Server
     */
    SERVER("server"),

    /**
     * Agent
     */
    AGENT("agent"),
    ;

    private final String type;
}
