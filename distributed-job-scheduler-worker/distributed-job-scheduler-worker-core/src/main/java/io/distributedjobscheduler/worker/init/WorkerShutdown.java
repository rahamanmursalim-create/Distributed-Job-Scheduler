package io.distributedjobscheduler.worker.init;

import io.distributedjobscheduler.common.request.WorkerStopRequest;
import io.distributedjobscheduler.common.response.ServerResponse;
import io.distributedjobscheduler.common.util.FutureUtil;
import io.distributedjobscheduler.worker.DistributedJobSchedulerWorker;
import io.distributedjobscheduler.worker.util.WorkerUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Slf4j
public class WorkerShutdown {
    private final DistributedJobSchedulerWorker distributedjobschedulerWorker;

    /**
     * Initialize status
     */
    private final AtomicBoolean isInit = new AtomicBoolean(false);

    public WorkerShutdown(DistributedJobSchedulerWorker distributedjobschedulerWorker) {
        this.distributedjobschedulerWorker = distributedjobschedulerWorker;
    }

    /**
     * Init
     */
    public void init() {
        // Already initialized
        if (this.isInit.get()) {
            return;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        // Initialized
        this.isInit.set(true);
    }

    /**
     * DistributedJobScheduler worker stop.
     */
    private void stop() {
        String serverAddress = WorkerConfig.getServerHost();
        String workerAddress = WorkerConfig.getWorkerAddress();
        String appName = WorkerConfig.getAppName();

        WorkerStopRequest stopRequest = new WorkerStopRequest();
        stopRequest.setAppName(appName);
        stopRequest.setAddress(workerAddress);

        try {
            FutureUtil.mustAsk(WorkerUtil.getServerWorkerActor(), stopRequest, ServerResponse.class, 3000L);
        } catch (Throwable e) {
            log.error("Stop worker fail. serverAddress={} workerAddress={} message={}", serverAddress, workerAddress, e.getMessage());
        }
    }

    /**
     * Shutdown
     */
    private void shutdown() {
        // Stop worker heartbeat service.
        this.distributedjobschedulerWorker.getWorkerHeartbeat().shutdown();

        // Stop worker initializer.
        this.distributedjobschedulerWorker.getWorkerInitializer().stop();

        // Stop worker.
        try {
            this.stop();
        } catch (Throwable e) {
            log.error("Worker stop failed", e);
        }
    }
}
