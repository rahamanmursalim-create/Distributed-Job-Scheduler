package io.distributedjobscheduler.server.cluster.task;

import io.distributedjobscheduler.common.DistributedJobSchedulerSpringContext;
import io.distributedjobscheduler.common.request.WorkerDelayStatusRequest;
import io.distributedjobscheduler.common.task.BaseConsumer;
import io.distributedjobscheduler.common.task.TaskQueue;
import io.distributedjobscheduler.server.scheduler.service.DelayInstanceService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
@Slf4j
public class WorkerDelayStatusConsumer extends BaseConsumer<WorkerDelayStatusRequest> {
    public WorkerDelayStatusConsumer(Long id,
                                     Integer consumerCoreThreadNum,
                                     Integer consumerMaxThreadNum,
                                     String consumerThreadName,
                                     Integer pollSize,
                                     String pollThreadName,
                                     TaskQueue<WorkerDelayStatusRequest> queues) {
        super(id, consumerCoreThreadNum, consumerMaxThreadNum, consumerThreadName, pollSize, pollThreadName, queues, 2000L, 1000L);
    }

    @Override
    public void consume(Long id, List<WorkerDelayStatusRequest> tasks) {
        this.consumerExecutor.submit(new WorkerDelayStatusRunnable(tasks));
    }

    private static class WorkerDelayStatusRunnable implements Runnable {
        private final List<WorkerDelayStatusRequest> tasks;

        private WorkerDelayStatusRunnable(List<WorkerDelayStatusRequest> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            try {
                this.tasks.forEach(r -> DistributedJobSchedulerSpringContext.getBean(DelayInstanceService.class).handleConsumerDelayStatus(r));
            } catch (Throwable throwable) {
                log.error("Worker delay status consume failed!", throwable);
            }
        }
    }
}
