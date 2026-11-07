package io.distributedjobscheduler.server.log.autoconfigure;

import io.distributedjobscheduler.server.log.client.Elasticsearch7Client;
import io.distributedjobscheduler.server.log.client.H2Client;
import io.distributedjobscheduler.server.log.client.MysqlClient;
import io.distributedjobscheduler.server.log.client.OracleClient;
import io.distributedjobscheduler.server.log.client.PostgresqlClient;
import io.distributedjobscheduler.server.log.constant.LogStorageConstant;
import io.distributedjobscheduler.server.log.dao.LogDAO;
import io.distributedjobscheduler.server.log.dao.impl.Elasticsearch7DAOImpl;
import io.distributedjobscheduler.server.log.dao.impl.H2LogDAOImpl;
import io.distributedjobscheduler.server.log.dao.impl.MysqlLogDAOImpl;
import io.distributedjobscheduler.server.log.dao.impl.OracleDAOImpl;
import io.distributedjobscheduler.server.log.dao.impl.PostgresqlDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfiguration {
    private final LogProperties logProperties;

    @Autowired
    public LogAutoConfiguration(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    @ConditionalOnProperty(prefix = "distributedjobscheduler.log.storage", name = "selector", havingValue = LogStorageConstant.H2)
    public class H2AutoConfiguration {
        @Bean
        public H2Client h2Client() {
            return new H2Client(logProperties.getStorage().getH2());
        }

        @Bean
        public LogDAO h2LogDAO(H2Client h2Client) {
            return new H2LogDAOImpl(h2Client);
        }
    }

    @ConditionalOnProperty(prefix = "distributedjobscheduler.log.storage", name = "selector", havingValue = LogStorageConstant.MYSQL)
    public class MysqlAutoConfiguration {
        @Bean
        public MysqlClient mysqlClient() {
            return new MysqlClient(logProperties.getStorage().getMysql());
        }

        @Bean
        public LogDAO h2LogDAO(MysqlClient mysqlClient) {
            return new MysqlLogDAOImpl(mysqlClient);
        }
    }

    @ConditionalOnProperty(prefix = "distributedjobscheduler.log.storage", name = "selector", havingValue = LogStorageConstant.POSTGRESQL)
    public class PostgresqlAutoConfiguration {
        @Bean
        public PostgresqlClient postgresqlClient() {
            return new PostgresqlClient(logProperties.getStorage().getPostgresql());
        }

        @Bean
        public LogDAO h2LogDAO(PostgresqlClient postgresqlClient) {
            return new PostgresqlDAOImpl(postgresqlClient);
        }
    }

    @ConditionalOnProperty(prefix = "distributedjobscheduler.log.storage", name = "selector", havingValue = LogStorageConstant.ORACLE)
    public class OracleAutoConfiguration {
        @Bean
        public OracleClient oracleClient() {
            return new OracleClient(logProperties.getStorage().getOracle());
        }

        @Bean
        public LogDAO h2LogDAO(OracleClient oracleClient) {
            return new OracleDAOImpl(oracleClient);
        }
    }

    @ConditionalOnProperty(prefix = "distributedjobscheduler.log.storage", name = "selector", havingValue = LogStorageConstant.ELASTICSEARCH7)
    public class Elasticsearch7autoconfiguration {
        @Bean
        public Elasticsearch7Client elasticsearch7Client() {
            return new Elasticsearch7Client(logProperties.getStorage().getElasticsearch7());
        }

        @Bean
        public LogDAO h2LogDAO(Elasticsearch7Client elasticsearch7Client) {
            return new Elasticsearch7DAOImpl(elasticsearch7Client, logProperties.getStorage().getElasticsearch7());
        }
    }
}
