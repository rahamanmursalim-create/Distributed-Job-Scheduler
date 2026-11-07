package io.distributedjobscheduler.worker.processor;

import io.distributedjobscheduler.worker.context.JobContext;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface MapReduceProcessor extends MapProcessor {
    /**
     * Processor reduce.
     *
     * @param jobContext jobContext
     * @return ProcessResult
     */
    ProcessResult reduce(JobContext jobContext);
}
