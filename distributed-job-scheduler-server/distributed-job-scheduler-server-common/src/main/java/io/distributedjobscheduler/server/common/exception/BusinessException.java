package io.distributedjobscheduler.server.common.exception;

/**
 * @author inhere
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
