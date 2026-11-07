package io.distributedjobscheduler.server.log.dao.impl;

import io.distributedjobscheduler.server.log.client.H2Client;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class H2LogDAOImpl extends JdbcDAOImpl {

    public H2LogDAOImpl(H2Client h2Client) {
        super(h2Client);
    }
}
