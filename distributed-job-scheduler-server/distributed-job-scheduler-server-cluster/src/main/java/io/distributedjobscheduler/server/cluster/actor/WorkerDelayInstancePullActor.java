package io.distributedjobscheduler.server.cluster.actor;

import io.distributedjobscheduler.common.actor.BaseActor;
import io.distributedjobscheduler.common.request.WorkerDelayPullRequest;
import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.common.response.ServerDelayPullResponse;
import io.distributedjobscheduler.server.scheduler.service.DelayInstanceService;
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
public class WorkerDelayInstancePullActor extends BaseActor {

    private final DelayInstanceService delayInstanceService;

    @Autowired
    public WorkerDelayInstancePullActor(DelayInstanceService delayInstanceService) {
        this.delayInstanceService = delayInstanceService;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(WorkerDelayPullRequest.class, this::handlePull)
                .build();
    }

    public void handlePull(WorkerDelayPullRequest pullRequest) {
        ServerDelayPullResponse pullResponse = this.delayInstanceService.pullInstance(pullRequest);
        getSender().tell(Result.success(pullResponse), getSelf());
    }
}
