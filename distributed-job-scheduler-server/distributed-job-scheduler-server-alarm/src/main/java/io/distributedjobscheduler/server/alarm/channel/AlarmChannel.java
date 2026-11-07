package io.distributedjobscheduler.server.alarm.channel;

import io.distributedjobscheduler.server.alarm.dto.AlarmDTO;
import io.distributedjobscheduler.server.repository.constant.AlertMethodEnum;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
public interface AlarmChannel {

    /**
     * Send
     *
     * @param alarmDTO alarmDTO
     */
    void send(AlarmDTO alarmDTO);

    /**
     * Channel type.
     *
     * @return AlertMethodEnum
     */
    AlertMethodEnum channel();
}
