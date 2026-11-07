package io.distributedjobscheduler.server.common.exception;

import io.distributedjobscheduler.server.common.constant.BaseEnum;
import lombok.Getter;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Getter
public class CodeException extends RuntimeException {
    /**
     * Base enum
     */
    private final BaseEnum baseEnum;

    /**
     * Args.
     */
    private final Object[] args;

    /**
     * New exception.
     *
     * @param baseEnum baseEnum
     * @param args     args
     * @param message  message
     */
    public CodeException(BaseEnum baseEnum, Object[] args, String message) {
        super(message);

        this.args = args;
        this.baseEnum = baseEnum;
    }

    /**
     * New exception.
     *
     * @param baseEnum baseEnum
     * @param args     args
     * @param message  message
     * @param cause    cause
     */
    public CodeException(BaseEnum baseEnum, Object[] args, String message, Throwable cause) {
        super(message, cause);

        this.args = args;
        this.baseEnum = baseEnum;
    }
}
