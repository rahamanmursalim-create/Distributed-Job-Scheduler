package io.distributedjobscheduler.worker.init;

import io.distributedjobscheduler.common.constant.ProtocolTypeEnum;
import io.distributedjobscheduler.common.request.WorkerStartRequest;
import io.distributedjobscheduler.common.response.ServerWorkerStartResponse;
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
public class WorkerRegister {

    private final DistributedJobSchedulerWorker distributedjobschedulerWorker;

    public WorkerRegister(DistributedJobSchedulerWorker distributedjobschedulerWorker) {
        this.distributedjobschedulerWorker = distributedjobschedulerWorker;
    }

    /**
     * Register
     */
    public void register() {
        String serverAddress = WorkerConfig.getServerHost();

        WorkerStartRequest startReq = new WorkerStartRequest();
        startReq.setAddress(WorkerConfig.getWorkerAddress());
        startReq.setAppName(WorkerConfig.getAppName());
        startReq.setProtocolType(ProtocolTypeEnum.AKKA.getType());

        try {
            ServerWorkerStartResponse response = FutureUtil.mustAsk(WorkerUtil.getServerWorkerActor(), startReq, ServerWorkerStartResponse.class, 15000L);
            log.info("Register worker success. serverAddress={} workerAddress={}", serverAddress, WorkerConfig.getWorkerAddress());

            // Do register.
            this.doRegister(response);
        } catch (Throwable e) {
            log.error("Register worker fail. serverAddress={} workerAddress={}", serverAddress, WorkerConfig.getWorkerAddress());
            throw e;
        }
    }

    /**
     * Do register.
     *
     * @param response response
     */
    private void doRegister(ServerWorkerStartResponse response) {
        this.distributedjobschedulerWorker.getWorkerContext().init(response.getAppId(), response.getWorkerAddressList());
    }
}
