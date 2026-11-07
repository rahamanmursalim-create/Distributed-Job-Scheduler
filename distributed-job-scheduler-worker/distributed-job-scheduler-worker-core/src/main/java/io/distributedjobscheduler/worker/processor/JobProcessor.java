package io.distributedjobscheduler.worker.processor;

import io.distributedjobscheduler.worker.context.JobContext;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface JobProcessor extends BaseProcessor {

    /**
     * Pre.
     *
     * @param context context
     * @throws Exception exception.
     */
    void preProcess(JobContext context) throws Exception;

    /**
     * Post.
     *
     * @param context context
     * @return ProcessResult
     * @throws Exception exception.
     */
    ProcessResult postProcess(JobContext context) throws Exception;
}
