package io.distributedjobscheduler.server.cluster.executor;

import io.distributedjobscheduler.common.task.TaskQueue;
import io.distributedjobscheduler.server.cluster.dto.WorkerHeartbeatReqDTO;
import io.distributedjobscheduler.server.cluster.task.WorkerHeartConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.3
 */
@Slf4j
@Component
public class WorkerHeartbeatExecutor {
    private final TaskQueue<WorkerHeartbeatReqDTO> queue;

    /**
     * New
     */
    public WorkerHeartbeatExecutor() {
        this.queue = new TaskQueue<>(0L, 64);

        //Consumer
        WorkerHeartConsumer consumer = new WorkerHeartConsumer(
                0L,
                1,
                16,
                "DistributedJobScheduler-heartbeat-executor",
                50,
                "DistributedJobScheduler-heartbeat-consumer",
                this.queue
        );
        consumer.start();
    }

    /**
     * Submit request
     *
     * @param request request
     */
    public void submit(WorkerHeartbeatReqDTO request) {
        try {
            this.queue.submit(request);
        } catch (InterruptedException e) {
            log.error("Worker heartbeat submit failed!", e);
        }
    }
}
