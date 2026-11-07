package io.distributedjobscheduler.server.scheduler.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(SchedulerProperties.class)
public class SchedulerAutoConfiguration {
}
