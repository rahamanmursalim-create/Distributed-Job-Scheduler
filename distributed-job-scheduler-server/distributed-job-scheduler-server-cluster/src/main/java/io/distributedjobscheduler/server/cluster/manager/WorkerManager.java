package io.distributedjobscheduler.server.cluster.manager;

import io.distributedjobscheduler.common.DistributedJobSchedulerSpringContext;
import io.distributedjobscheduler.common.constant.CommonConstant;
import io.distributedjobscheduler.common.util.DateUtil;
import io.distributedjobscheduler.server.cluster.autoconfigure.ClusterProperties;
import io.distributedjobscheduler.server.cluster.data.RefreshData;
import io.distributedjobscheduler.server.cluster.dto.WorkerFailDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerJoinDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerStartReqDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerStartRespDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerStopReqDTO;
import io.distributedjobscheduler.server.cluster.dto.WorkerStopRespDTO;
import io.distributedjobscheduler.server.cluster.util.ClusterUtil;
import io.distributedjobscheduler.server.common.ClusterContext;
import io.distributedjobscheduler.server.common.util.SlotsUtil;
import io.distributedjobscheduler.server.repository.constant.WorkerStatusEnum;
import io.distributedjobscheduler.server.repository.dao.AppDAO;
import io.distributedjobscheduler.server.repository.dao.WorkerDAO;
import io.distributedjobscheduler.server.repository.entity.App;
import io.distributedjobscheduler.server.repository.entity.Worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.8
 */
@Slf4j
@Component
public class WorkerManager {
    private final WorkerDAO workerDAO;
    private final AppDAO appDAO;
    private final ClusterProperties clusterProperties;
    private final RefreshData refreshManager;

    @Autowired
    public WorkerManager(WorkerDAO workerDAO, AppDAO appDAO, ClusterProperties clusterProperties, RefreshData refreshManager) {
        this.workerDAO = workerDAO;
        this.appDAO = appDAO;
        this.clusterProperties = clusterProperties;
        this.refreshManager = refreshManager;
    }

    /**
     * Worker start
     *
     * @param reqDTO start request.
     */
    public WorkerStartRespDTO workerStart(WorkerStartReqDTO reqDTO) {
        // Check app name.
        App app = this.checkAppName(reqDTO.getAppName());

        // Do worker start.
        DistributedJobSchedulerSpringContext.getBean(this.getClass()).doWorkerStart(reqDTO, app);

        // Akka message for worker start.
        WorkerJoinDTO workerJoinDTO = new WorkerJoinDTO();
        workerJoinDTO.setClusterVersion(ClusterContext.getSystem().getClusterVersion());
        ClusterUtil.sendMessage(workerJoinDTO, ClusterContext.getCurrentNode(), this.clusterProperties.getSpreadSize(), new HashSet<>());

        // Online workers  and exclude start worker.
        Set<String> onlineWorkers = ClusterUtil.getOnlineWorkers(app.getId());

        // Response
        WorkerStartRespDTO response = new WorkerStartRespDTO();
        response.setAppId(app.getId());
        response.setAppName(app.getName());
        response.setWorkerAddressList(onlineWorkers);
        return response;
    }

    /**
     * Do worker start.
     *
     * @param reqDTO reqDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public void doWorkerStart(WorkerStartReqDTO reqDTO, App app) {
        // Refresh system.
        // Lock system cluster version.
        this.refreshManager.refreshSystem(true);

        // Update worker status.
        this.updateWorkerForStart(reqDTO, app);

        // Refresh cluster context.
        refreshClusterContext();
    }

    /**
     * Worker stop
     *
     * @param stopReq stop request.
     */
    public WorkerStopRespDTO workerStop(WorkerStopReqDTO stopReq) {
        // Check app name.
        this.checkAppName(stopReq.getAppName());

        // Do worker stop.
        DistributedJobSchedulerSpringContext.getBean(this.getClass()).doWorkerStop(stopReq);

        // Akka message for worker start.
        WorkerFailDTO workerFailDTO = new WorkerFailDTO();
        workerFailDTO.setClusterVersion(ClusterContext.getSystem().getClusterVersion());
        ClusterUtil.sendMessage(workerFailDTO, ClusterContext.getCurrentNode(), this.clusterProperties.getSpreadSize(), new HashSet<>());
        return new WorkerStopRespDTO();
    }

    /**
     * Do worker stop.
     *
     * @param stopReq stopReq
     */
    @Transactional(rollbackFor = Exception.class)
    public void doWorkerStop(WorkerStopReqDTO stopReq) {
        // Refresh system.
        // Lock system cluster version.
        this.refreshManager.refreshSystem(true);

        Worker worker = workerDAO.getByAddress(stopReq.getAddress());
        if (Objects.isNull(worker)) {
            log.error("worker({}) do not exist!", stopReq.getAddress());
            return;
        }

        // Update worker
        worker.setStatus(WorkerStatusEnum.OFFLINE.getStatus());
        worker.setUpdateTime(DateUtil.timestamp());
        workerDAO.save(worker);

        // Refresh cluster context.
        this.refreshClusterContext();
    }

    private void refreshClusterContext() {
        List<Worker> workers = workerDAO.listOnlineWorkers();
        ClusterUtil.refreshAppWorkers(workers);

        log.info("Refresh app workers={}", workers.stream().map(Worker::getAddress).collect(Collectors.toList()));
    }

    /**
     * Check app name.
     *
     * @param appName app name.
     * @return App
     */
    private App checkAppName(String appName) {
        App app = appDAO.getAppByName(appName);
        if (Objects.isNull(app)) {
            throw new RuntimeException(String.format("Register application(%s) do not exist!", appName));
        }
        return app;
    }

    private void updateWorkerForStart(WorkerStartReqDTO startReq, App app) {
        // Update worker.
        long now = DateUtil.timestamp();
        Worker worker = workerDAO.getByAddress(startReq.getAddress());
        if (Objects.nonNull(worker)) {
            worker.setWorkerKey(worker.getWorkerKey());
            worker.setStatus(WorkerStatusEnum.ONLINE.getStatus());

            //Must update last heartbeat time.
            worker.setLastHeartbeatTime(now);
            worker.setUpdateTime(now);

            // Update latest app name
            worker.setAppName(startReq.getAppName());
            worker.setNamespaceId(app.getNamespaceId());
            worker.setAppId(app.getId());
            worker.setProtocolType(startReq.getProtocolType());
            workerDAO.save(worker);
            return;
        }

        // save
        Worker saveWorker = new Worker();
        saveWorker.setUpdateTime(now);
        saveWorker.setCreateTime(now);
        saveWorker.setWorkerKey(startReq.getWorkerKey());
        saveWorker.setAddress(startReq.getAddress());
        saveWorker.setSlotsId(SlotsUtil.getWorkerSupervisorSlotsId(UUID.randomUUID().toString()));
        saveWorker.setStatus(WorkerStatusEnum.ONLINE.getStatus());
        saveWorker.setAppId(app.getId());
        saveWorker.setAppName(startReq.getAppName());
        saveWorker.setLastHeartbeatTime(now);
        saveWorker.setNamespaceId(app.getNamespaceId());
        saveWorker.setProtocolType(startReq.getProtocolType());
        saveWorker.setVersion(startReq.getVersion());
        saveWorker.setMetric("");
        saveWorker.setVersion("");
        saveWorker.setWorkerKey("");
        saveWorker.setDeleteTime(0L);
        saveWorker.setDeleted(CommonConstant.NO);
        workerDAO.save(saveWorker);
    }
}
