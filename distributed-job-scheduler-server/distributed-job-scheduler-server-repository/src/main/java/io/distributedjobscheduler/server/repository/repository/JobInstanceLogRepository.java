package io.distributedjobscheduler.server.repository.repository;

import io.distributedjobscheduler.server.repository.entity.JobInstanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface JobInstanceLogRepository extends JpaRepository<JobInstanceLog, Long> {

    /**
     * Find by job instance id.
     *
     * @param jobInstanceId jobInstanceId
     * @return List
     */
    List<JobInstanceLog> findByJobInstanceIdOrderByCreateTimeAsc(Long jobInstanceId);
}
