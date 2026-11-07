package io.distributedjobscheduler.worker.delay;

import akka.actor.ActorSelection;
import com.google.common.collect.Lists;
import io.distributedjobscheduler.common.request.WorkerDelayItemPullRequest;
import io.distributedjobscheduler.common.request.WorkerDelayPullRequest;
import io.distributedjobscheduler.common.response.ServerDelayInstanceResponse;
import io.distributedjobscheduler.common.response.ServerDelayPullResponse;
import io.distributedjobscheduler.common.util.DateUtil;
import io.distributedjobscheduler.common.util.FutureUtil;
import io.distributedjobscheduler.worker.config.DistributedJobSchedulerConfig;
import io.distributedjobscheduler.worker.constant.WorkerConstant;
import io.distributedjobscheduler.worker.dao.DelayDAO;
import io.distributedjobscheduler.worker.dto.DelayInstanceDTO;
import io.distributedjobscheduler.worker.entity.Delay;
import io.distributedjobscheduler.worker.init.WorkerConfig;
import io.distributedjobscheduler.worker.util.WorkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Slf4j
public class DelayTaskMasterExecutor implements Runnable {
    private final Long pullSleep;
    private final Long pullStep;

    public DelayTaskMasterExecutor() {
        this.pullStep = DistributedJobSchedulerConfig.getLong(WorkerConstant.WORKER_DELAY_PULL_STEP, WorkerConstant.DEFAULT_WORKER_DELAY_PULL_STEP);
        this.pullSleep = DistributedJobSchedulerConfig.getLong(WorkerConstant.WORKER_DELAY_PULL_SLEEP, WorkerConstant.DEFAULT_WORKER_DELAY_PULL_SLEEP);
    }

    @Override
    @SuppressWarnings("all")
    public void run() {
        while (true) {
            try {
                this.start();
            } catch (Throwable e) {
                log.error("DelayTaskMasterExecutor start failed!", e);

                // Optimize start error
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException ex) {
                    log.error("DelayTaskMasterExecutor start failed!", ex);
                }
            }
        }
    }

    private void start() throws InterruptedException {
        // Pull topic items.
        List<WorkerDelayItemPullRequest> pullTopicItems = Lists.newArrayList();
        Set<Long> pullTopicIds = new HashSet<>();

        int maxPullSize = DistributedJobSchedulerConfig.getInteger(WorkerConstant.WORKER_DELAY_PULL_SIZE, WorkerConstant.DEFAULT_WORKER_DELAY_PULL_SIZE);

        // Find pull topic.
        DelayDAO.INSTANCE.findPullList()
                .forEach(d -> {
                    int pullSize = d.getPullSize() > maxPullSize ? maxPullSize : d.getPullSize();
                    pullTopicItems.add(new WorkerDelayItemPullRequest(d.getTopic(), pullSize));
                    pullTopicIds.add(d.getId());
                });

        // Empty pull.
        if (CollectionUtils.isEmpty(pullTopicItems)) {
            Thread.sleep(this.pullSleep);
            return;
        }

        // Pull request.
        WorkerDelayPullRequest delayPullRequest = new WorkerDelayPullRequest();
        delayPullRequest.setWorkerAddress(WorkerConfig.getWorkerAddress());
        delayPullRequest.setPullItems(pullTopicItems);

        // Pull delay instance by akka.
        ActorSelection instanceActor = WorkerUtil.getServerDelayInstancePullActor();
        ServerDelayPullResponse delayPullResponse = FutureUtil.mustAsk(instanceActor, delayPullRequest, ServerDelayPullResponse.class, 3000L);

        //  All topic empty.
        if (CollectionUtils.isEmpty(delayPullResponse.getDelayInstanceResponses())) {
            Thread.sleep(this.pullSleep);
            return;
        }

        // Group by topic.
        Map<Long, List<ServerDelayInstanceResponse>> topicIdsMap = delayPullResponse.getDelayInstanceResponses().stream()
                .collect(Collectors.groupingBy(ServerDelayInstanceResponse::getDelayId));

        // Update pull time.
        this.updatePullTime(pullTopicIds, topicIdsMap.keySet());

        // Execute delay task.
        this.execute(topicIdsMap);
    }

    private void updatePullTime(Set<Long> pullTopicIds, Set<Long> responseTopicIds) {
        Long now = DateUtil.timestamp();
        long nowMill = DateUtil.milliLongTime();
        List<Delay> updateDelays = Lists.newArrayList();
        pullTopicIds.forEach(id -> {
            Delay delay = new Delay();
            delay.setId(id);
            delay.setUpdateTime(now);

            // Next pull time.
            if (!responseTopicIds.contains(id)) {
                delay.setPullTime(nowMill + this.pullStep);
            } else {
                // Reset pull time.
                delay.setPullTime(0L);
            }
        });

        // Batch update pull time.
        if (!CollectionUtils.isEmpty(updateDelays)) {
            DelayDAO.INSTANCE.batchUpdatePullTime(updateDelays);
        }
    }

    private void execute(Map<Long, List<ServerDelayInstanceResponse>> topicIdMap) {
        topicIdMap.forEach((t, instanceResponses) -> {
            ServerDelayInstanceResponse firstDelay = instanceResponses.get(0);
            DelayTaskContainer delayTaskContainer = this.getDelayTaskContainer(firstDelay);
            List<DelayInstanceDTO> instanceList = instanceResponses.stream().map(i -> {
                DelayInstanceDTO delayInstanceDTO = new DelayInstanceDTO();
                delayInstanceDTO.setTopic(i.getTopic());
                delayInstanceDTO.setDelayId(i.getDelayId());
                delayInstanceDTO.setDelayPid(i.getDelayPid());
                delayInstanceDTO.setDelayParams(i.getDelayParams());
                delayInstanceDTO.setDelayExtra(i.getDelayExtra());
                delayInstanceDTO.setProcessorInfo(i.getProcessorInfo());
                delayInstanceDTO.setFailRetryInterval(i.getFailRetryInterval());
                delayInstanceDTO.setFailRetryTimes(i.getFailRetryTimes());
                delayInstanceDTO.setExecuteTimeout(i.getExecuteTimeout());
                delayInstanceDTO.setConcurrency(i.getConcurrency());
                delayInstanceDTO.setTaskId(i.getTaskId());
                return delayInstanceDTO;
            }).collect(Collectors.toList());

            // Executor task list.
            delayTaskContainer.execute(instanceList);
        });
    }

    /**
     * Get delay container
     *
     * @param firstDelay firstDelay
     * @return DelayTaskContainer
     */
    private DelayTaskContainer getDelayTaskContainer(@Nonnull ServerDelayInstanceResponse firstDelay) {
        // Delay fail topic
        if (firstDelay.getDelayPid() > 0) {
            DelayTaskContainer delayTaskContainer = DelayTaskContainerPool.get(
                    firstDelay.getDelayId(),
                    id -> new DelayTaskContainer(firstDelay.getDelayId(), firstDelay.getBlockingSize(), firstDelay.getFailTopicConcurrency())
            );

            // Update concurrency
            delayTaskContainer.updateConcurrency(firstDelay.getFailTopicConcurrency());
            return delayTaskContainer;
        }

        // Delay topic
        DelayTaskContainer delayTaskContainer = DelayTaskContainerPool.get(
                firstDelay.getDelayId(),
                id -> new DelayTaskContainer(firstDelay.getDelayId(), firstDelay.getBlockingSize(), firstDelay.getConcurrency())
        );

        // Update concurrency
        delayTaskContainer.updateConcurrency(firstDelay.getConcurrency());
        return delayTaskContainer;
    }
}
