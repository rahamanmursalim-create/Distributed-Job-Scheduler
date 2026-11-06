package io.distributedjobscheduler.server.repository.repository;

import io.distributedjobscheduler.server.repository.entity.AlertRule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.5
 */
public interface AlertRuleRepository extends JpaRepository<AlertRule, Long> {

}
