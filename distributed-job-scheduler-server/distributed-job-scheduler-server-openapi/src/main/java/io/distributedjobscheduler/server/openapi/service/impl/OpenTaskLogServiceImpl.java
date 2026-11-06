package io.distributedjobscheduler.server.openapi.service.impl;

import io.distributedjobscheduler.server.cluster.dto.WorkerJobInstanceTaskLogReqDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerJobInstanceTaskLogRespDTO;
import io.distributedjobscheduler.server.cluster.manager.JobInstanceTaskLogManager;
import io.distributedjobscheduler.server.common.util.BeanMapperUtil;
import io.distributedjobscheduler.server.openapi.request.WorkerJobInstanceTaskLogRequest;
import io.distributedjobscheduler.server.openapi.service.OpenTaskLogService;
import io.distributedjobscheduler.server.openapi.vo.WorkerJobInstanceTaskLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.8
 */
@Service
public class OpenTaskLogServiceImpl implements OpenTaskLogService {

    private final JobInstanceTaskLogManager jobInstanceTaskLogManager;

    @Autowired
    public OpenTaskLogServiceImpl(JobInstanceTaskLogManager jobInstanceTaskLogManager) {
        this.jobInstanceTaskLogManager = jobInstanceTaskLogManager;
    }

    @Override
    public WorkerJobInstanceTaskLogVO batchAdd(WorkerJobInstanceTaskLogRequest logRequest) {
        WorkerJobInstanceTaskLogRespDTO respDTO = this.jobInstanceTaskLogManager.handleInstanceTaskLog(BeanMapperUtil.map(logRequest, WorkerJobInstanceTaskLogReqDTO.class));
        return BeanMapperUtil.map(respDTO, WorkerJobInstanceTaskLogVO.class);
    }
}
