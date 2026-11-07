package io.distributedjobscheduler.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ProtocolTypeEnum {
    /**
     * Akka.
     */
    AKKA("akka"),

    /**
     * Http
     */
    HTTP("http"),
    ;

    private final String type;
}
