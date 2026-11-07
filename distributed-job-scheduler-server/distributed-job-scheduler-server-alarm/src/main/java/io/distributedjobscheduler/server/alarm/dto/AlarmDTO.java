package io.distributedjobscheduler.server.alarm.dto;

import io.distributedjobscheduler.server.repository.entity.AlertRule;
import io.distributedjobscheduler.server.repository.entity.App;
import io.distributedjobscheduler.server.repository.entity.Delay;
import io.distributedjobscheduler.server.repository.entity.Job;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
@Data
public class AlarmDTO {
    private AlertRule alertRule;
    private AlarmEventDTO event;
    private App app;
    private Job job;
    private Delay delay;
}
