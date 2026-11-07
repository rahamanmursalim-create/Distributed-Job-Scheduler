package io.distributedjobscheduler.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum BoolIntEnum {
    /**
     * Yes is true
     */
    YES(1),

    /**
     * NO: false
     */
    NO(2),
    ;

    private final Integer type;
}
