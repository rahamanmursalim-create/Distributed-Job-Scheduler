package io.distributedjobscheduler.worker.actor;

import akka.actor.AbstractActor;
import io.distributedjobscheduler.common.request.ServerWorkerHeartbeatRequest;
import io.distributedjobscheduler.common.response.Result;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class WorkerHeartbeatActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ServerWorkerHeartbeatRequest.class, this::workerHeartbeatCheckRequest)
                .build();
    }

    public void workerHeartbeatCheckRequest(ServerWorkerHeartbeatRequest workerHeartbeatCheckRequest) {
        getSender().tell(Result.success(new Object()), getSelf());
    }
}
