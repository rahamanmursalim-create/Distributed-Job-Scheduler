package io.distributedjobscheduler.server.openapi.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(ClusterProperties.class)
public class ClusterAutoConfiguration {
}
