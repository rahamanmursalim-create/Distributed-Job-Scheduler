package io.distributedjobscheduler.server.admin.service;

import io.distributedjobscheduler.server.admin.request.server.JobSlotRequest;
import io.distributedjobscheduler.server.admin.request.server.ServerListRequest;
import io.distributedjobscheduler.server.admin.vo.server.JobSlotListVO;
import io.distributedjobscheduler.server.admin.vo.server.ServerListVO;
import io.distributedjobscheduler.server.common.vo.PageVO;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface ServerService {

    /**
     * List Server by page
     *
     * @param request list parmas
     * @return PageVO
     */
    PageVO<ServerListVO> getServerList(ServerListRequest request);

    /**
     * List job slot by page
     *
     * @param request request
     * @return PageVO
     */
    PageVO<JobSlotListVO> getJobSlotsList(JobSlotRequest request);
}
