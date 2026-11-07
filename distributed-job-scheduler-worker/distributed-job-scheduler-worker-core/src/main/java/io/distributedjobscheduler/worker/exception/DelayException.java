package io.distributedjobscheduler.worker.exception;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class DelayException extends RuntimeException {
    public DelayException(String message) {
        super(message);
    }
}
