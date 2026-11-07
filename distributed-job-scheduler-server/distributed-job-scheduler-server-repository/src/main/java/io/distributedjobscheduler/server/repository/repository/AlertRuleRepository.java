package io.distributedjobscheduler.server.repository.repository;

import io.distributedjobscheduler.server.repository.entity.AlertRule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.5
 */
public interface AlertRuleRepository extends JpaRepository<AlertRule, Long> {

}
