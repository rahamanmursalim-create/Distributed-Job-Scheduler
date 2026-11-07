package io.distributedjobscheduler.common.util;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class RuntimeUtil {
    /**
     * Get available processors.
     *
     * @return Integer
     */
    public static Integer processors() {
        return Runtime.getRuntime().availableProcessors();
    }
}
