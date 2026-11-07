package io.distributedjobscheduler.server.log.client;

import io.distributedjobscheduler.server.log.autoconfigure.LogProperties;
import io.distributedjobscheduler.server.log.constant.LogJdbcDriverConstant;

import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.4
 */
public class OracleClient extends AbstractJdbcHikariClient {
    private final LogProperties.OracleProperties oracleProperties;

    public OracleClient(LogProperties.OracleProperties oracleProperties) {
        this.oracleProperties = oracleProperties;
    }

    @Override
    public void initTable() throws SQLException {
    }

    @Override
    public void afterPropertiesSet() {
        // Driver
        LogProperties.JdbcProperties properties = oracleProperties.getProperties();
        if (Objects.isNull(properties.getDriver())) {
            properties.setDriver(LogJdbcDriverConstant.ORACLE);
        }

        this.init(properties);
    }
}
