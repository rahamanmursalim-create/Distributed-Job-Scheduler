package io.distributedjobscheduler.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.4
 */
@Getter
@AllArgsConstructor
public enum ShellTypeEnum {

    /**
     * unix
     */
    UNIX("unix"),

    /**
     * windows
     */
    WINDOWS("windows"),
    ;

    /**
     * Type
     */
    private final String type;
}
