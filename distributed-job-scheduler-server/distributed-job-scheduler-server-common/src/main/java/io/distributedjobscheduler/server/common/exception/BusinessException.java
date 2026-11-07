package io.distributedjobscheduler.server.common.exception;

/**
 * @author Distributed Job Scheduler
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
