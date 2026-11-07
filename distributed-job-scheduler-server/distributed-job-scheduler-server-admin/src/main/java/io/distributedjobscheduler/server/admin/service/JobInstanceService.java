package io.distributedjobscheduler.server.admin.service;

import io.distributedjobscheduler.server.admin.request.job.DeleteJobInstanceRequest;
import io.distributedjobscheduler.server.admin.request.job.ListJobInstanceRequest;
import io.distributedjobscheduler.server.admin.request.job.ListProcessorLogRequest;
import io.distributedjobscheduler.server.admin.request.job.StopJobInstanceRequest;
import io.distributedjobscheduler.server.admin.vo.job.DeleteJobInstanceVO;
import io.distributedjobscheduler.server.admin.vo.job.ListJobInstanceVO;
import io.distributedjobscheduler.server.admin.vo.job.ListProcessorLogVO;
import io.distributedjobscheduler.server.admin.vo.job.StopJobInstanceVO;
import io.distributedjobscheduler.server.common.vo.PageVO;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface JobInstanceService {

    /**
     * List job instance.
     *
     * @param request request
     * @return PageVO
     */
    PageVO<ListJobInstanceVO> getPageList(ListJobInstanceRequest request);

    /**
     * Kill instance
     *
     * @param killRequest killRequest
     * @return return
     */
    StopJobInstanceVO stopInstance(StopJobInstanceRequest killRequest);

    /**
     * Delete job instance.
     *
     * @param request request
     * @return DeleteJobInstanceVO
     */
    DeleteJobInstanceVO deleteInstance(DeleteJobInstanceRequest request);

    /**
     * Get processor log.
     *
     * @param request request
     * @return ListProcessorLogVO
     */
    ListProcessorLogVO getProcessorList(ListProcessorLogRequest request);
}
