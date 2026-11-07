package io.distributedjobscheduler.server.admin.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Distributed Job Scheduler
 */
@Configuration
@EnableConfigurationProperties(AdminUserProperties.class)
public class AdminAutoConfiguration {
}
