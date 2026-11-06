package io.distributedjobscheduler.server.cluster.actor;

import io.distributedjobscheduler.common.actor.BaseActor;
import io.distributedjobscheduler.common.request.WorkerStartRequest;
import io.distributedjobscheduler.common.request.WorkerStopRequest;
import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.common.response.ServerResponse;
import io.distributedjobscheduler.common.response.ServerWorkerStartResponse;
import io.distributedjobscheduler.server.cluster.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Component
@Slf4j
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkerActor extends BaseActor {

    private final WorkerService workerService;

    @Autowired
    public WorkerActor(WorkerService workerService) {
        this.workerService = workerService;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(WorkerStartRequest.class, this::workerStart)
                .match(WorkerStopRequest.class, this::workerStop)
                .build();
    }

    /**
     * Worker start
     *
     * @param workerStartRequest start request.
     */
    public void workerStart(WorkerStartRequest workerStartRequest) {
        ServerWorkerStartResponse response = this.workerService.workerStart(workerStartRequest);

        log.info("Worker register success! address={}", workerStartRequest.getAddress());
        getSender().tell(Result.success(response), getSelf());
    }

    /**
     * Worker stop.
     *
     * @param workerStopRequest workerStopRequest
     */
    public void workerStop(WorkerStopRequest workerStopRequest) {
        this.workerService.workerStop(workerStopRequest);

        log.info("Worker stop success! address={}", workerStopRequest.getAddress());
        getSender().tell(Result.success(new ServerResponse()), getSelf());
    }
}
