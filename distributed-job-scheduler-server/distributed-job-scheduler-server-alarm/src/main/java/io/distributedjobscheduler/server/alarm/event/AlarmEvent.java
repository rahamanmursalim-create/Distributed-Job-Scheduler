package io.distributedjobscheduler.server.alarm.event;

import io.distributedjobscheduler.server.alarm.dto.AlarmEventDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
public class AlarmEvent extends ApplicationEvent {
    public AlarmEvent(AlarmEventDTO alarmEventDTO) {
        super(alarmEventDTO);
    }
}
