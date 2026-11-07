package io.distributedjobscheduler.server.log.dao.impl;

import io.distributedjobscheduler.server.log.client.MysqlClient;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class MysqlLogDAOImpl extends JdbcDAOImpl {
    public MysqlLogDAOImpl(MysqlClient mysqlClient) {
        super(mysqlClient);
    }
}
