package io.distributedjobscheduler.server.repository.repository;

import io.distributedjobscheduler.server.repository.entity.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface SystemRepository extends JpaRepository<System, Integer> {
    /**
     * Update cluster version
     *
     * @param id             id
     * @param clusterVersion clusterVersion
     * @return effect rows.
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("update System set clusterVersion=clusterVersion + 1 where id=?1 and clusterVersion=?2")
    Integer updateClusterVersion(Integer id, Long clusterVersion);

    /**
     * Update delay cluster version.
     *
     * @param id                  id
     * @param clusterDelayVersion clusterDelayVersion
     * @return Integer
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("update System  set clusterDelayVersion=clusterDelayVersion + 1 where id=?1 and clusterDelayVersion=?2")
    Integer updateClusterDelayVersion(Integer id, Long clusterDelayVersion);
}
