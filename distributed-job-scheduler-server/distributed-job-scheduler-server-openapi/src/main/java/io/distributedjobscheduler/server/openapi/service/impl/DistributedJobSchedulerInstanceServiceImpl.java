package io.distributedjobscheduler.server.openapi.service.impl;

import io.distributedjobscheduler.server.cluster.dto.WorkerJobInstanceStatusReqDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerJobInstanceStatusRespDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerJobInstanceTaskBatchReqDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerJobInstanceTaskBatchRespDTO;
import io.distributedjobscheduler.server.cluster.manager.JobInstanceManager;
import io.distributedjobscheduler.server.common.util.BeanMapperUtil;
import io.distributedjobscheduler.server.openapi.request.WorkerJobInstanceStatusRequest;
import io.distributedjobscheduler.server.openapi.request.WorkerJobInstanceTaskBatchRequest;
import io.distributedjobscheduler.server.openapi.service.DistributedJobSchedulerInstanceService;
import io.distributedjobscheduler.server.openapi.vo.WorkerJobInstanceStatusVO;
import io.distributedjobscheduler.server.openapi.vo.WorkerJobInstanceTaskBatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
@Service
public class DistributedJobSchedulerInstanceServiceImpl implements DistributedJobSchedulerInstanceService {

    private final JobInstanceManager jobInstanceManager;

    @Autowired
    public DistributedJobSchedulerInstanceServiceImpl(JobInstanceManager jobInstanceManager) {
        this.jobInstanceManager = jobInstanceManager;
    }

    @Override
    public WorkerJobInstanceStatusVO handleStatus(WorkerJobInstanceStatusRequest statusRequest) {
        WorkerJobInstanceStatusRespDTO respDTO = this.jobInstanceManager.handleInstanceStatus(BeanMapperUtil.map(statusRequest, WorkerJobInstanceStatusReqDTO.class));
        return BeanMapperUtil.map(respDTO, WorkerJobInstanceStatusVO.class);
    }

    @Override
    public WorkerJobInstanceTaskBatchVO handleTasks(WorkerJobInstanceTaskBatchRequest batchRequest) {
        WorkerJobInstanceTaskBatchRespDTO respDTO = this.jobInstanceManager.handleInstanceTasks(BeanMapperUtil.map(batchRequest, WorkerJobInstanceTaskBatchReqDTO.class));
        return BeanMapperUtil.map(respDTO, WorkerJobInstanceTaskBatchVO.class);
    }
}
