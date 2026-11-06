package io.distributedjobscheduler.worker.actor;

import io.distributedjobscheduler.common.actor.BaseActor;
import io.distributedjobscheduler.common.request.ServerDelayInstanceStopRequest;
import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.common.response.WorkerResponse;
import io.distributedjobscheduler.worker.delay.DelayTaskManager;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
public class DelayTaskMasterActor extends BaseActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ServerDelayInstanceStopRequest.class, this::stopDelayInstance)
                .build();
    }

    public void stopDelayInstance(ServerDelayInstanceStopRequest request) {
        DelayTaskManager.INSTANCE.stopAndRemoveTaskInstance(request.getTaskId());
        getSender().tell(Result.success(new WorkerResponse()), getSelf());
    }
}
