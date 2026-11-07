package io.distributedjobscheduler.worker.delay;

import io.distributedjobscheduler.common.request.WorkerDelayTaskRequest;
import io.distributedjobscheduler.worker.task.DelayStatusConsumer;
import io.distributedjobscheduler.common.task.TaskQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Slf4j
public class DelayStatusReporter {
    private static final TaskQueue<WorkerDelayTaskRequest> TASK_QUEUE;
    private static final DelayStatusConsumer TASK_CONSUMER;

    static {
        TASK_QUEUE = new TaskQueue<>(0L, 1024);
        TASK_CONSUMER = new DelayStatusConsumer(
                0L,
                1,
                1,
                "DistributedJobScheduler-delay-status",
                50,
                "DistributedJobScheduler-delay-status-consumer",
                TASK_QUEUE
        );

        TASK_CONSUMER.start();
    }

    /**
     * Report delay status.
     *
     * @param workerDelayStatusRequest workerDelayStatusRequest
     */
    public static void report(WorkerDelayTaskRequest workerDelayStatusRequest) {
        try {
            TASK_QUEUE.submit(workerDelayStatusRequest);
        } catch (InterruptedException e) {
            log.error("Delay status report failed!", e);
        }
    }
}
