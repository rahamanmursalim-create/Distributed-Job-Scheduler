package io.distributedjobscheduler.server.cluster.service;

import io.distributedjobscheduler.common.request.WorkerJobInstanceTaskLogRequest;
import io.distributedjobscheduler.server.cluster.dto.WorkerJobInstanceTaskLogReqDTO;
import io.distributedjobscheduler.server.cluster.manager.JobInstanceTaskLogManager;
import io.distributedjobscheduler.server.common.util.BeanMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Slf4j
@Service
public class JobInstanceTaskLogService {
    private final JobInstanceTaskLogManager jobInstanceTaskLogManager;

    @Autowired
    public JobInstanceTaskLogService(JobInstanceTaskLogManager jobInstanceTaskLogManager) {
        this.jobInstanceTaskLogManager = jobInstanceTaskLogManager;
    }


    /**
     * Handle instance log.
     *
     * @param logReq log request.
     */
    public void handleInstanceTaskLog(WorkerJobInstanceTaskLogRequest logReq) {
        this.jobInstanceTaskLogManager.handleInstanceTaskLog(BeanMapperUtil.map(logReq, WorkerJobInstanceTaskLogReqDTO.class));
    }
}
