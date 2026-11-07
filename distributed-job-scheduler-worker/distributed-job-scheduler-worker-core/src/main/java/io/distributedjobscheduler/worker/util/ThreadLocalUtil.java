package io.distributedjobscheduler.worker.util;

import io.distributedjobscheduler.worker.context.JobContext;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class ThreadLocalUtil {
    private static final ThreadLocal<JobContext> JOB_CONTEXT_LOCAL = new ThreadLocal<>();


    public static void setJobContext(JobContext jobContext) {
        JOB_CONTEXT_LOCAL.set(jobContext);
    }

    public static JobContext getJobContext() {
        return JOB_CONTEXT_LOCAL.get();
    }

    public static void removeJobContext() {
        JOB_CONTEXT_LOCAL.remove();
    }
}
