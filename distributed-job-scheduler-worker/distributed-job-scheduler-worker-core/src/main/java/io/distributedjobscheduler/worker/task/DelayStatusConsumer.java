package io.distributedjobscheduler.worker.task;

import io.distributedjobscheduler.common.request.WorkerDelayStatusRequest;
import io.distributedjobscheduler.common.request.WorkerDelayTaskRequest;
import io.distributedjobscheduler.common.task.BaseConsumer;
import io.distributedjobscheduler.common.task.TaskQueue;
import io.distributedjobscheduler.worker.init.WorkerActorSystem;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class DelayStatusConsumer extends BaseConsumer<WorkerDelayTaskRequest> {

    public DelayStatusConsumer(Long id,
                               Integer consumerCoreThreadNum,
                               Integer consumerMaxThreadNum,
                               String consumerThreadName,
                               Integer pollSize,
                               String pollThreadName,
                               TaskQueue<WorkerDelayTaskRequest> queues) {
        super(id, consumerCoreThreadNum, consumerMaxThreadNum, consumerThreadName, pollSize, pollThreadName, queues);
    }

    @Override
    public void consume(Long id, List<WorkerDelayTaskRequest> tasks) {
        consumerExecutor.submit(new DelayStatusConsumerRunnable(tasks));
    }

    private static class DelayStatusConsumerRunnable implements Runnable {
        private final List<WorkerDelayTaskRequest> tasks;

        private DelayStatusConsumerRunnable(List<WorkerDelayTaskRequest> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            WorkerDelayStatusRequest workerDelayStatusRequest = new WorkerDelayStatusRequest();
            workerDelayStatusRequest.setTaskList(tasks);
            WorkerActorSystem.atLeastOnceDelivery(workerDelayStatusRequest, null);
        }
    }
}
