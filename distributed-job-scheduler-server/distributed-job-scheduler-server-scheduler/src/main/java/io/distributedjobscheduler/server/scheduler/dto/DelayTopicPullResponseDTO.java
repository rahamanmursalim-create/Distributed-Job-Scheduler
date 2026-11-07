package io.distributedjobscheduler.server.scheduler.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class DelayTopicPullResponseDTO {
    private List<DelayTopicPullDTO> topicList;
}
