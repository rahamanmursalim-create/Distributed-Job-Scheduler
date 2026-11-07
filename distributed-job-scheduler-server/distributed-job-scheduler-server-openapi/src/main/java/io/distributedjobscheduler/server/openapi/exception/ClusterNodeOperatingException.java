package io.distributedjobscheduler.server.openapi.exception;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class ClusterNodeOperatingException extends RuntimeException {

    /**
     * New exception.
     *
     * @param message message
     */
    public ClusterNodeOperatingException(String message) {
        super(message);
    }
}
