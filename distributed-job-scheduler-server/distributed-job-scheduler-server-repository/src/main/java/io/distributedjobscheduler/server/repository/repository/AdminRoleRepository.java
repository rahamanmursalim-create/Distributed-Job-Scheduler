package io.distributedjobscheduler.server.repository.repository;

import io.distributedjobscheduler.server.repository.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {
    /**
     * Find by ID In given list
     *
     * @param ids ids
     * @return list
     */
    List<AdminRole> findByIdIn(Collection<Long> ids);

}

