package io.distributedjobscheduler.server.scheduler.scheduling;

import io.distributedjobscheduler.server.scheduler.service.ClearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.2
 */
@Component
public class ClearScheduling {
    private final ClearService clearService;

    @Autowired
    public ClearScheduling(ClearService clearService) {
        this.clearService = clearService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void clearData() {
        this.clearService.clearData();
    }
}
