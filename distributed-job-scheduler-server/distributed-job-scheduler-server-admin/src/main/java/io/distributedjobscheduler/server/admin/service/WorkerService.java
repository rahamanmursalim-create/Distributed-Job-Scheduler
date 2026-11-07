package io.distributedjobscheduler.server.admin.service;

import io.distributedjobscheduler.server.admin.request.worker.WorkerListRequest;
import io.distributedjobscheduler.server.admin.vo.worker.WorkerListVO;
import io.distributedjobscheduler.server.common.vo.PageVO;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface WorkerService {

    /**
     * List Worker by page
     *
     * @param request params
     * @return result
     */
    PageVO<WorkerListVO> getPage(WorkerListRequest request);
}
