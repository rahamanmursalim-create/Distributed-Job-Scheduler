package io.distributedjobscheduler.worker.master;

import akka.actor.ActorContext;
import akka.actor.ActorSelection;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.distributedjobscheduler.common.constant.TaskConstant;
import io.distributedjobscheduler.common.constant.TimeExpressionTypeEnum;
import io.distributedjobscheduler.common.response.WorkerResponse;
import io.distributedjobscheduler.common.util.FutureUtil;
import io.distributedjobscheduler.worker.dto.JobInstanceDTO;
import io.distributedjobscheduler.worker.entity.Task;
import io.distributedjobscheduler.worker.request.ContainerBatchTaskStatusRequest;
import io.distributedjobscheduler.worker.request.MasterStartContainerRequest;
import io.distributedjobscheduler.worker.util.AddressUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Slf4j
public class StandaloneTaskMaster extends AbstractTaskMaster {
    private ScheduledExecutorService secondDelayService;

    public StandaloneTaskMaster(JobInstanceDTO jobInstanceDTO, ActorContext actorContext) {
        super(jobInstanceDTO, actorContext);
    }

    @Override
    protected void init() {
        super.init();

        // Second delay scheduler
        if (this.isSecondDelay()) {
            this.secondDelayService = new ScheduledThreadPoolExecutor(
                    1,
                    new ThreadFactoryBuilder().setNameFormat("DistributedJobScheduler-heartbeat-thread").build(),
                    new ThreadPoolExecutor.AbortPolicy()
            );

            // Runnable
            this.secondDelayService.scheduleWithFixedDelay(() -> {
                try {
                    this.doTaskComplete();
                } catch (Throwable throwable) {
                    log.error("Standalone do complete failed!");
                }
            }, 5, 1, TimeUnit.SECONDS);
        }
    }

    @Override
    public void stop(Integer type) {
        // Second delay to shut down scheduler
        if (this.isSecondDelay()) {
            this.secondDelayService.shutdown();
        }

        super.stop(type);
    }

    @Override
    public void updateStatus(ContainerBatchTaskStatusRequest batchRequest) {
        // Update list
        List<Task> updateList = batchRequest.getTaskStatusList().stream().map(s -> {
            String taskUniqueId = s.getTaskUniqueId();
            return new Task(taskUniqueId, s.getStatus(), s.getResult());
        }).collect(Collectors.toList());

        // Update by status.
        updateList.stream().collect(Collectors.groupingBy(Task::getStatus))
                .forEach((status, groupList) -> taskDAO.batchUpdateStatusByTaskId(groupList, status));

        // Second delay scheduler
        // Async to do complete
        if (this.isSecondDelay()) {
            return;
        }

        // Standalone task.
        // Do Complete task.
        if (this.isTaskComplete(this.jobInstanceDTO.getJobInstanceId(), this.circleIdGenerator.get())) {
            try {
                this.completeTask();
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    @Override
    public void submit() {
        // Super submit
        super.submit();

        // Create container
        MasterStartContainerRequest startRequest = this.getMasterStartContainerRequest();

        // Task
        Task task = this.convertToTask(startRequest, AddressUtil.getWorkerAddressByLocal(this.localWorkerAddress));
        task.setTaskParentId(TaskConstant.DEFAULT_PARENT_ID);
        taskDAO.add(task);

        // Add address.
        this.containerWorkers.add(AddressUtil.getWorkerAddressByLocal(this.localWorkerAddress));

        ActorSelection actorSelection = actorContext.actorSelection(this.localContainerPath);
        FutureUtil.mustAsk(actorSelection, startRequest, WorkerResponse.class, 3000L);

        // Add task manager
        this.addTask2Manager();

        // Running status.
        this.running.set(true);
    }

    private void doTaskComplete() {
        // Not running
        if (!this.running.get()) {
            return;
        }

        // Standalone task.
        // Do Complete task.
        if (this.isTaskComplete(this.jobInstanceDTO.getJobInstanceId(), this.circleIdGenerator.get())) {
            try {
                this.completeTask();
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
