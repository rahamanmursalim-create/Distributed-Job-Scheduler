package io.distributedjobscheduler.server.admin.vo.home;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.2
 */
@Data
public class SystemDataVO {
    private DataItemVO app;
    private DataItemVO server;
    private DataItemVO worker;
    private DataItemVO slot;
}
