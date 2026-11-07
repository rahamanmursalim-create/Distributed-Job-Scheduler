package io.distributedjobscheduler.server.cluster.actor;

import io.distributedjobscheduler.common.actor.BaseActor;
import io.distributedjobscheduler.common.request.WorkerHeartbeatRequest;
import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.common.response.ServerHeartbeatResponse;
import io.distributedjobscheduler.server.cluster.service.WorkerHeartbeatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Component
@Log4j2
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkerHeartbeatActor extends BaseActor {

    private final WorkerHeartbeatService workerHeartbeatService;

    @Autowired
    public WorkerHeartbeatActor(WorkerHeartbeatService workerHeartbeatService) {
        this.workerHeartbeatService = workerHeartbeatService;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(WorkerHeartbeatRequest.class, this::workerHeartbeat)
                .build();
    }

    /**
     * Worker heartbeat request.
     *
     * @param workerHeartbeatRequest heartbeat request.
     */
    public void workerHeartbeat(WorkerHeartbeatRequest workerHeartbeatRequest) {
        ServerHeartbeatResponse response = workerHeartbeatService.workerHeartbeat(workerHeartbeatRequest);
        getSender().tell(Result.success(response), getSelf());
    }
}
