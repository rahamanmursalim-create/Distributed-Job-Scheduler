package io.distributedjobscheduler.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@Getter
@AllArgsConstructor
public enum HttpMethodEnum {
    /**
     * GET
     */
    FORM("GET"),

    /**
     * POST
     */
    JSON("POST"),
    ;

    private final String type;
}
