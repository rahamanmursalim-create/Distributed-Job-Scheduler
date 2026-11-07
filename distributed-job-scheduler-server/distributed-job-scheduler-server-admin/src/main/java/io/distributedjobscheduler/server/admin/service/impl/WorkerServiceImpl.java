package io.distributedjobscheduler.server.admin.service.impl;

import io.distributedjobscheduler.server.admin.request.worker.WorkerListRequest;
import io.distributedjobscheduler.server.admin.service.WorkerService;
import io.distributedjobscheduler.server.admin.vo.worker.WorkerListVO;
import io.distributedjobscheduler.server.common.dto.PageDTO;
import io.distributedjobscheduler.server.common.util.BeanMapperUtil;
import io.distributedjobscheduler.server.common.util.PageUtil;
import io.distributedjobscheduler.server.common.vo.PageVO;
import io.distributedjobscheduler.server.repository.dao.WorkerDAO;
import io.distributedjobscheduler.server.repository.dto.WorkerPageDTO;
import io.distributedjobscheduler.server.repository.entity.Worker;
import org.springframework.stereotype.Service;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerDAO workerDAO;

    public WorkerServiceImpl(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }

    @Override
    public PageVO<WorkerListVO> getPage(WorkerListRequest request) {
        PageDTO<Worker> paging = this.workerDAO.getPage(BeanMapperUtil.map(request, WorkerPageDTO.class));
        return PageUtil.convert(paging, n -> BeanMapperUtil.map(n, WorkerListVO.class));
    }
}
