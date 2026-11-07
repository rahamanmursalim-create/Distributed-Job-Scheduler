package io.distributedjobscheduler.worker.processor;

import io.distributedjobscheduler.worker.context.JobContext;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface BaseProcessor {
    /**
     * Process
     *
     * @param context job context.
     * @return ProcessResult
     * @throws Exception exception.
     */
    ProcessResult process(JobContext context) throws Exception;

    /**
     * stop.
     *
     * @param context context
     * @throws Exception exception
     */
    void stop(JobContext context) throws Exception;
}
