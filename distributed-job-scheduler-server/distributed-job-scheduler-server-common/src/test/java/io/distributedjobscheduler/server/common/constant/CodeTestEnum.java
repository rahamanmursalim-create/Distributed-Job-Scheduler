package io.distributedjobscheduler.server.common.constant;

import io.distributedjobscheduler.server.common.exception.CodeExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CodeTestEnum implements CodeExceptionAssert {
    ONE(1, "code message");

    private final Integer value;
    private final String message;
}
