package io.distributedjobscheduler.server.repository.dao;

import io.distributedjobscheduler.server.common.dto.PageDTO;
import io.distributedjobscheduler.server.repository.dto.WorkerPageDTO;
import io.distributedjobscheduler.server.repository.entity.Worker;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface WorkerDAO {

    /**
     * Save.
     *
     * @param worker worker
     * @return Long
     */
    Long save(Worker worker);

    /**
     * List all workers.
     *
     * @param slotsIds slot ids.
     * @return List
     */
    List<Worker> listAllWorkersBySlotIds(List<Long> slotsIds);

    /**
     * Get by address.
     *
     * @param address address
     * @return Worker
     */
    Worker getByAddress(String address);

    /**
     * List online workers.
     *
     * @return List
     */
    List<Worker> listOnlineWorkers();


    /**
     * Count by namespace id
     *
     * @param namespaceId namespaceId
     * @param status      status
     * @return Long
     */
    Long countByNamespaceIdAndStatus(Long namespaceId, Integer status);

    /**
     * Delete by create time and status
     *
     * @param lastTime lastTime
     * @param status   status
     * @return Long
     */
    Long deleteByCreateTimeAndStatus(Long lastTime, Integer status);

    /**
     * Update heartbeat time
     *
     * @param addresses         addresses
     * @param lastHeartbeatTime lastHeartbeatTime
     */
    void updateLastHeartbeatTimeByAddresses(List<String> addresses, Long lastHeartbeatTime);

    /**
     * page result
     *
     * @param workerPageDTO pageDTO
     * @return result
     */
    PageDTO<Worker> getPage(WorkerPageDTO workerPageDTO);
}
