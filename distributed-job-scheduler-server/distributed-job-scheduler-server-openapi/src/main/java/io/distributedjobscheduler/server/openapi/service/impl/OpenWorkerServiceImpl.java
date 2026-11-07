package io.distributedjobscheduler.server.openapi.service.impl;

import io.distributedjobscheduler.server.cluster.dto.WorkerHeartbeatReqDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerHeartbeatRespDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerStartReqDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerStartRespDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerStopReqDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerStopRespDTO;
import io.distributedjobscheduler.server.cluster.manager.WorkerHeartbeatManager;
import io.distributedjobscheduler.server.cluster.manager.WorkerManager;
import io.distributedjobscheduler.server.common.util.BeanMapperUtil;
import io.distributedjobscheduler.server.openapi.request.WorkerHeartbeatRequest;
import io.distributedjobscheduler.server.openapi.request.WorkerStartRequest;
import io.distributedjobscheduler.server.openapi.request.WorkerStopRequest;
import io.distributedjobscheduler.server.openapi.service.OpenWorkerService;
import io.distributedjobscheduler.server.openapi.vo.ServerHeartbeatVO;
import io.distributedjobscheduler.server.openapi.vo.ServerWorkerStartVO;
import io.distributedjobscheduler.server.openapi.vo.ServerWorkerStopVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Service
@Log4j2
public class OpenWorkerServiceImpl implements OpenWorkerService {
    private final WorkerManager workerManager;
    private final WorkerHeartbeatManager workerHeartbeatManager;

    @Autowired
    public OpenWorkerServiceImpl(WorkerManager workerManager, WorkerHeartbeatManager workerHeartbeatManager) {
        this.workerManager = workerManager;
        this.workerHeartbeatManager = workerHeartbeatManager;
    }

    @Override
    public ServerWorkerStartVO workerStart(WorkerStartRequest startRequest) {
        WorkerStartReqDTO reqDTO = BeanMapperUtil.map(startRequest, WorkerStartReqDTO.class);
        WorkerStartRespDTO workerStartRespDTO = this.workerManager.workerStart(reqDTO);
        return BeanMapperUtil.map(workerStartRespDTO, ServerWorkerStartVO.class);
    }

    @Override
    public ServerWorkerStopVO workerStop(WorkerStopRequest stopReq) {
        WorkerStopReqDTO reqDTO = BeanMapperUtil.map(stopReq, WorkerStopReqDTO.class);
        WorkerStopRespDTO workerStopRespDTO = this.workerManager.workerStop(reqDTO);
        return BeanMapperUtil.map(workerStopRespDTO, ServerWorkerStopVO.class);
    }

    @Override
    public ServerHeartbeatVO workerHeartbeat(WorkerHeartbeatRequest workerHeartbeatRequest) {
        WorkerHeartbeatRespDTO workerHeartbeatRespDTO = this.workerHeartbeatManager.workerHeartbeat(BeanMapperUtil.map(workerHeartbeatRequest, WorkerHeartbeatReqDTO.class));
        return BeanMapperUtil.map(workerHeartbeatRespDTO, ServerHeartbeatVO.class);
    }
}
