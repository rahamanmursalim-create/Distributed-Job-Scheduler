package io.distributedjobscheduler.server.cluster.executor;

import io.distributedjobscheduler.common.task.TaskQueue;
import io.distributedjobscheduler.server.cluster.dto.WorkerJobInstanceTaskBatchReqDTO;
import io.distributedjobscheduler.server.cluster.task.WorkerJobInstanceTaskConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
@Slf4j
@Component
public class WorkerJobInstanceTaskExecutor {
    private final TaskQueue<WorkerJobInstanceTaskBatchReqDTO> queue;

    /**
     * New
     */
    public WorkerJobInstanceTaskExecutor() {
        this.queue = new TaskQueue<>(0L, 1024);

        //Consumer
        WorkerJobInstanceTaskConsumer consumer = new WorkerJobInstanceTaskConsumer(
                0L,
                1,
                32,
                "DistributedJobScheduler-instance-task-executor",
                4,
                "DistributedJobScheduler-instance-task-consumer",
                this.queue
        );
        consumer.start();
    }

    /**
     * Submit request
     *
     * @param request request
     */
    public void submit(WorkerJobInstanceTaskBatchReqDTO request) {
        try {
            this.queue.submit(request);
        } catch (InterruptedException e) {
            log.error("Worker heartbeat submit failed!", e);
        }
    }
}
