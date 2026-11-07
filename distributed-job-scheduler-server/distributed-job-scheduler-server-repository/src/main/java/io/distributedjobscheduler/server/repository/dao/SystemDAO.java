package io.distributedjobscheduler.server.repository.dao;

import io.distributedjobscheduler.server.repository.entity.System;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface SystemDAO {

    /**
     * Get one.
     *
     * @return system
     */
    System getOne();

    /**
     * Update by id
     *
     * @param clusterVersion cluster version.
     * @return effect rows.
     */
    Integer updateClusterVersion(Long clusterVersion);

    /**
     * Update cluster delay version
     *
     * @param clusterDelayVersion clusterDelayVersion
     * @return Integer
     */
    Integer updateClusterDelayVersion(Long clusterDelayVersion);


    /**
     * update by id
     *
     * @param system update Params
     */
    void updateById(System system);
}
