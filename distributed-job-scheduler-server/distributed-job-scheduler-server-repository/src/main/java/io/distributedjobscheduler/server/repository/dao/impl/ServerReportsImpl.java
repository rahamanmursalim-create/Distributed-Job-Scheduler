package io.distributedjobscheduler.server.repository.dao.impl;

import io.distributedjobscheduler.common.constant.CommonConstant;
import io.distributedjobscheduler.common.util.DateUtil;
import io.distributedjobscheduler.server.repository.dao.ServerReportsDAO;
import io.distributedjobscheduler.server.repository.entity.Server;
import io.distributedjobscheduler.server.repository.entity.ServerReports;
import io.distributedjobscheduler.server.repository.repository.ServerReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Component
public class ServerReportsImpl implements ServerReportsDAO {
    private final ServerReportsRepository serverReportsRepository;

    @Autowired
    public ServerReportsImpl(ServerReportsRepository serverReportsRepository) {
        this.serverReportsRepository = serverReportsRepository;
    }

    @Override
    public Long save(ServerReports serverFailReports) {
        Long now = DateUtil.timestamp();
        serverFailReports.setDeleted(CommonConstant.NO);
        serverFailReports.setDeleteTime(0L);
        serverFailReports.setUpdateTime(now);
        serverFailReports.setCreateTime(now);
        return serverReportsRepository.saveAndFlush(serverFailReports).getId();
    }

    @Override
    public Long countServerReports(Long startTime, Long serverId, Integer status) {
        return serverReportsRepository.countByCreateTimeGreaterThanEqualAndServerIdAndStatus(startTime, serverId, status);
    }
}
