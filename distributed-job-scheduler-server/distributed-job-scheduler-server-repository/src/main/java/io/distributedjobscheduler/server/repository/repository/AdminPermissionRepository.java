package io.distributedjobscheduler.server.repository.repository;

import io.distributedjobscheduler.server.repository.entity.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface AdminPermissionRepository extends JpaRepository<AdminPermission, Long> {
    /**
     * Find by ID In given list
     *
     * @param ids ids
     * @return list
     */
    List<AdminPermission> findByIdIn(Collection<Long> ids);
}

