package io.distributedjobscheduler.server.common.constant;

import io.distributedjobscheduler.server.common.exception.CodeExceptionAssert;
import io.distributedjobscheduler.server.common.exception.HttpStatusExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum HttpStatusTestEnum implements HttpStatusExceptionAssert {
    ONE(1, "http status message");

    private final Integer value;
    private final String message;
}
