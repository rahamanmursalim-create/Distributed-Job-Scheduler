package io.distributedjobscheduler.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
@AllArgsConstructor
@Getter
public enum MediaTypeEnum {
    /**
     * Form
     */
    FORM("application/x-www-form-urlencoded"),

    /**
     * JSON
     */
    JSON("application/json"),
    ;

    private final String type;
}
