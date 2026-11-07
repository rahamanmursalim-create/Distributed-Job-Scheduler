package io.distributedjobscheduler.server.admin.service.impl;

import io.distributedjobscheduler.server.admin.request.system.SystemUpdateRequest;
import io.distributedjobscheduler.server.admin.service.SystemService;
import io.distributedjobscheduler.server.admin.vo.system.SystemUpdateVO;
import io.distributedjobscheduler.server.admin.vo.system.SystemVO;
import io.distributedjobscheduler.server.common.util.BeanMapperUtil;
import io.distributedjobscheduler.server.repository.dao.SystemDAO;
import io.distributedjobscheduler.server.repository.entity.System;
import org.springframework.stereotype.Service;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Service
public class SystemServiceImpl implements SystemService {

    private final SystemDAO systemDAO;

    public SystemServiceImpl(SystemDAO systemDAO) {
        this.systemDAO = systemDAO;
    }

    @Override
    public SystemVO getLatest() {
        System adminSystem = this.systemDAO.getOne();
        return BeanMapperUtil.map(adminSystem, SystemVO.class);
    }

    @Override
    public SystemUpdateVO update(SystemUpdateRequest request) {
        this.systemDAO.updateById(BeanMapperUtil.map(request, System.class));
        return new SystemUpdateVO();
    }
}
