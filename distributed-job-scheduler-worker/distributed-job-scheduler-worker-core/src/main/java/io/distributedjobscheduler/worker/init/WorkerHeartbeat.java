package io.distributedjobscheduler.worker.init;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.distributedjobscheduler.common.request.WorkerHeartbeatRequest;
import io.distributedjobscheduler.common.response.ServerHeartbeatResponse;
import io.distributedjobscheduler.common.util.FutureUtil;
import io.distributedjobscheduler.worker.DistributedJobSchedulerWorker;
import io.distributedjobscheduler.worker.config.DistributedJobSchedulerConfig;
import io.distributedjobscheduler.worker.constant.WorkerConstant;
import io.distributedjobscheduler.worker.master.TaskMasterPool;
import io.distributedjobscheduler.worker.util.WorkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Slf4j
public class WorkerHeartbeat {
    private final DistributedJobSchedulerWorker distributedjobschedulerWorker;

    /**
     * Worker heartbeat
     */
    private final ScheduledExecutorService heartbeatService;

    /**
     * Fail counter
     */
    private final AtomicInteger failCounter = new AtomicInteger(0);

    /**
     * New WorkerHeartbeat
     *
     * @param distributedjobschedulerWorker distributedjobschedulerWorker
     */
    public WorkerHeartbeat(DistributedJobSchedulerWorker distributedjobschedulerWorker) {
        this.distributedjobschedulerWorker = distributedjobschedulerWorker;
        this.heartbeatService = new ScheduledThreadPoolExecutor(
                1,
                new ThreadFactoryBuilder().setNameFormat("DistributedJobScheduler-heartbeat-thread").build(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /**
     * Init
     */
    public void init() {
        int heartbeatInterval = DistributedJobSchedulerConfig.getInteger(WorkerConstant.WORKER_HEARTBEAT_INTERVAL, WorkerConstant.DEFAULT_WORKER_HEARTBEAT_INTERVAL);
        heartbeatService.scheduleAtFixedRate(() -> {
            try {
                // Fixed terminate task, when an execution of the task throws an exception.
                this.doWorkerHeartbeat();
            } catch (Throwable throwable) {
                log.error("Do worker heartbeat failed!", throwable);
            }
        }, 5, heartbeatInterval, TimeUnit.SECONDS);
    }

    /**
     * Stop heartbeat.
     */
    public void shutdown() {
        // Stop worker heartbeat service.
        heartbeatService.shutdownNow();
    }

    private void doWorkerHeartbeat() {
        int failTimes = DistributedJobSchedulerConfig.getInteger(WorkerConstant.WORKER_HEARTBEAT_FAIL_TIMES, WorkerConstant.DEFAULT_WORKER_HEARTBEAT_FAIL_TIMES);
        String workerAddress = WorkerConfig.getWorkerAddress();
        String serverAddress = WorkerConfig.getServerHost();

        WorkerHeartbeatRequest heartbeatReq = new WorkerHeartbeatRequest();
        heartbeatReq.setAppId(WorkerContext.getAppId());
        heartbeatReq.setAddress(workerAddress);
        heartbeatReq.setAppName(DistributedJobSchedulerConfig.getString(WorkerConstant.WORKER_APP_NAME));
        heartbeatReq.setVersion("1.0");
        heartbeatReq.setRunningJobInstanceIds(TaskMasterPool.getRunningTask());
        try {
            //Heartbeat
            ServerHeartbeatResponse heartbeatResponse = FutureUtil.mustAsk(WorkerUtil.getServerHeartbeatActor(), heartbeatReq, ServerHeartbeatResponse.class, 3000L);

            // Refresh worker.
            this.refresh(heartbeatResponse);

            // Reset counter
            this.failCounter.set(0);
        } catch (Throwable e) {
            int count = this.failCounter.incrementAndGet();
            log.error(String.format("Worker heartbeat fail. serverAddress=%s workerAddress=%s failTimes=%s", serverAddress, workerAddress, count), e);

            if (count >= failTimes) {
                log.info("Begin to refresh server! server={} port={} failTimes={}", WorkerConfig.getServerHost(), WorkerConfig.getServerPort(), failTimes);
                WorkerConfig.refreshServer();

                // Reset counter
                this.failCounter.set(0);
            }
        }
    }

    /**
     * Refresh worker.
     */
    private void refresh(ServerHeartbeatResponse heartbeatResponse) {
        // Refresh online workers.
        this.refreshOnlineWorkers(heartbeatResponse);

        // Refresh delay.
        if (WorkerConfig.getDelayEnable()) {
            this.distributedjobschedulerWorker.getWorkerInitializer().getDelayManager().refresh(heartbeatResponse.getSystemResponse());
        }
    }

    /**
     * Refresh online workers.
     *
     * @param heartbeatResponse heartbeatResponse
     */
    private void refreshOnlineWorkers(ServerHeartbeatResponse heartbeatResponse) {
        // Offline workers.
        // Must before refresh context online workers.
        Set<String> offlineWorkers = new HashSet<>(WorkerContext.getOnlineWorkers());
        offlineWorkers.removeAll(heartbeatResponse.getWorkerAddressList());
        if (!CollectionUtils.isEmpty(offlineWorkers)) {
            log.info("Offline workers! workers={}", offlineWorkers);
            TaskMasterPool.offlineWorkers(offlineWorkers);
        }

        // Online worker unique.
        String onlineWorkerUnique = heartbeatResponse.getWorkerAddressList()
                .stream()
                .sorted(Comparator.comparing(String::hashCode))
                .collect(Collectors.toList()).toString();

        // Context worker unique.
        String contextWorkerUnique = WorkerContext.getOnlineWorkers()
                .stream()
                .sorted(Comparator.comparing(String::hashCode))
                .collect(Collectors.toList()).toString();

        // Refresh online workers.
        if (!onlineWorkerUnique.equals(contextWorkerUnique)) {
            this.distributedjobschedulerWorker.getWorkerContext().refreshOnlineWorkers(heartbeatResponse.getWorkerAddressList());
        }
    }
}
