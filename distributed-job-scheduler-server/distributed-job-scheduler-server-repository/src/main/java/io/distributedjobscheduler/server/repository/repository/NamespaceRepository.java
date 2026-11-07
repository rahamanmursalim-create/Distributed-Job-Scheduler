package io.distributedjobscheduler.server.repository.repository;

import io.distributedjobscheduler.server.repository.entity.Namespace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface NamespaceRepository extends JpaRepository<Namespace, Long> {
}
