package io.distributedjobscheduler.server.scheduler.service;

import io.distributedjobscheduler.common.constant.FailStatusEnum;
import io.distributedjobscheduler.common.constant.TaskStatusEnum;
import io.distributedjobscheduler.common.request.WorkerDelayAddRequest;
import io.distributedjobscheduler.common.request.WorkerDelayPullRequest;
import io.distributedjobscheduler.common.request.WorkerDelayStatusRequest;
import io.distributedjobscheduler.common.request.WorkerDelayTaskRequest;
import io.distributedjobscheduler.common.request.WorkerDelayTopicPullRequest;
import io.distributedjobscheduler.common.response.ServerDelayAddResponse;
import io.distributedjobscheduler.common.response.ServerDelayInstanceResponse;
import io.distributedjobscheduler.common.response.ServerDelayPullResponse;
import io.distributedjobscheduler.common.response.ServerDelayTopicPullResponse;
import io.distributedjobscheduler.common.response.ServerDelayTopicResponse;
import io.distributedjobscheduler.server.alarm.constant.AlarmEventEnum;
import io.distributedjobscheduler.server.alarm.dto.AlarmEventDTO;
import io.distributedjobscheduler.server.alarm.event.AlarmEvent;
import io.distributedjobscheduler.server.alarm.event.AlarmEventPublisher;
import io.distributedjobscheduler.server.common.util.BeanMapperUtil;
import io.distributedjobscheduler.server.scheduler.dto.DelayInstanceAddRequestDTO;
import io.distributedjobscheduler.server.scheduler.dto.DelayInstanceAddResponseDTO;
import io.distributedjobscheduler.server.scheduler.dto.DelayInstancePullResponseDTO;
import io.distributedjobscheduler.server.scheduler.dto.DelayInstanceStatusRequestDTO;
import io.distributedjobscheduler.server.scheduler.dto.DelayItemPullRequestDTO;
import io.distributedjobscheduler.server.scheduler.dto.DelayTopicPullDTO;
import io.distributedjobscheduler.server.scheduler.dto.DelayTopicPullRequestDTO;
import io.distributedjobscheduler.server.scheduler.dto.DelayTopicPullResponseDTO;
import io.distributedjobscheduler.server.scheduler.scheduler.DelayInstanceScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Slf4j
@Service
public class DelayInstanceService {
    private final DelayInstanceScheduler delayInstanceScheduler;

    @Autowired
    public DelayInstanceService(DelayInstanceScheduler delayInstanceScheduler) {
        this.delayInstanceScheduler = delayInstanceScheduler;
    }

    /**
     * Pull instance.
     *
     * @param pullRequest pull request.
     * @return ServerDelayPullResponse
     */
    public ServerDelayPullResponse pullInstance(WorkerDelayPullRequest pullRequest) {
        List<ServerDelayInstanceResponse> responses = new ArrayList<>();

        // Pull delay instance.
        pullRequest.getPullItems().forEach(item -> {
            DelayItemPullRequestDTO delayItemPullRequestDTO = BeanMapperUtil.map(item, DelayItemPullRequestDTO.class);
            List<DelayInstancePullResponseDTO> responseList = this.delayInstanceScheduler.pullByTopic(pullRequest.getWorkerAddress(), delayItemPullRequestDTO);
            responses.addAll(BeanMapperUtil.mapList(responseList, DelayInstancePullResponseDTO.class, ServerDelayInstanceResponse.class));
        });

        ServerDelayPullResponse delayPullResponse = new ServerDelayPullResponse();
        delayPullResponse.setDelayInstanceResponses(responses);
        return delayPullResponse;
    }

    /**
     * Add delay instance.
     *
     * @param addRequest add request.
     * @return ServerDelayAddResponse
     */
    public ServerDelayAddResponse addDelayInstance(WorkerDelayAddRequest addRequest) {
        ServerDelayAddResponse serverDelayAddResponse = new ServerDelayAddResponse();
        DelayInstanceAddRequestDTO addRequestDTO = BeanMapperUtil.map(addRequest, DelayInstanceAddRequestDTO.class);
        DelayInstanceAddResponseDTO addResponseDTO = this.delayInstanceScheduler.add(addRequestDTO);
        serverDelayAddResponse.setTaskId(addResponseDTO.getTaskId());
        return serverDelayAddResponse;
    }

    /**
     * Pull topic list.
     *
     * @param pullRequest pullRequest
     * @return ServerDelayTopicPullResponse
     */
    public ServerDelayTopicPullResponse pullTopicList(WorkerDelayTopicPullRequest pullRequest) {
        DelayTopicPullRequestDTO delayTopicPullRequestDTO = BeanMapperUtil.map(pullRequest, DelayTopicPullRequestDTO.class);

        DelayTopicPullResponseDTO topicListDTO = this.delayInstanceScheduler.pullTopicList(delayTopicPullRequestDTO);
        ServerDelayTopicPullResponse response = new ServerDelayTopicPullResponse();
        response.setTopicList(BeanMapperUtil.mapList(topicListDTO.getTopicList(), DelayTopicPullDTO.class, ServerDelayTopicResponse.class));
        return response;
    }

    /**
     * Handle delay status.
     *
     * @param workerDelayStatusRequest workerDelayStatusRequest
     */
    public void handleConsumerDelayStatus(WorkerDelayStatusRequest workerDelayStatusRequest) {
        List<DelayInstanceStatusRequestDTO> statusList = BeanMapperUtil.mapList(workerDelayStatusRequest.getTaskList(), WorkerDelayTaskRequest.class, DelayInstanceStatusRequestDTO.class);

        this.addAlarmEvent(statusList);
        this.delayInstanceScheduler.report(statusList);
    }

    protected void addAlarmEvent(List<DelayInstanceStatusRequestDTO> statusList) {
        statusList.forEach(s -> {
            if (TaskStatusEnum.isFailed(s.getStatus())) {
                AlarmEventDTO alarmEventDTO = new AlarmEventDTO();
                alarmEventDTO.setJobUniqueId(s.getTopic());
                alarmEventDTO.setInstanceId(s.getTaskId());

                // Delay worker task status(fail/timeout)
                if (FailStatusEnum.isExecuteFail(s.getFailStatus())) {
                    alarmEventDTO.setName(AlarmEventEnum.DELAY_EXECUTE_FAIL.getEvent());
                } else {
                    alarmEventDTO.setName(AlarmEventEnum.DELAY_EXECUTE_TIMEOUT.getEvent());
                }

                // Event message
                alarmEventDTO.setMessage(s.getResult());
                AlarmEventPublisher.publishEvent(new AlarmEvent(alarmEventDTO));
            }
        });
    }
}
