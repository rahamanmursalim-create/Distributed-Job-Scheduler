package io.distributedjobscheduler.server.log.client;

import io.distributedjobscheduler.server.log.autoconfigure.LogProperties;
import io.distributedjobscheduler.server.log.constant.LogJdbcDriverConstant;

import java.util.Objects;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
public class MysqlClient extends AbstractJdbcHikariClient {
    private final LogProperties.MysqlProperties mysqlProperties;

    /**
     * New mysql client.
     *
     * @param mysqlProperties mysql properties
     */
    public MysqlClient(LogProperties.MysqlProperties mysqlProperties) {
        this.mysqlProperties = mysqlProperties;
    }

    @Override
    public void afterPropertiesSet() {
        // Driver
        LogProperties.JdbcProperties properties = mysqlProperties.getProperties();
        if (Objects.isNull(properties.getDriver())) {
            properties.setDriver(LogJdbcDriverConstant.MYSQL);
        }

        this.init(properties);
    }
}
