package io.distributedjobscheduler.server.admin.service;

import io.distributedjobscheduler.server.admin.request.delay.DeleteDelayInstanceRequest;
import io.distributedjobscheduler.server.admin.request.delay.DeleteDelayRequest;
import io.distributedjobscheduler.server.admin.request.delay.ListDelayInstanceLogRequest;
import io.distributedjobscheduler.server.admin.request.delay.ListDelayInstanceRequest;
import io.distributedjobscheduler.server.admin.request.delay.StopDelayInstanceRequest;
import io.distributedjobscheduler.server.admin.vo.delay.DeleteDelayInstanceVO;
import io.distributedjobscheduler.server.admin.vo.delay.ListDelayInstanceLogVO;
import io.distributedjobscheduler.server.admin.vo.delay.ListDelayInstanceVO;
import io.distributedjobscheduler.server.admin.vo.delay.StopDelayInstanceVO;
import io.distributedjobscheduler.server.common.vo.PageVO;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
public interface DelayInstanceService {

    /**
     * Page list
     *
     * @param request request
     * @return PageVO
     */
    PageVO<ListDelayInstanceVO> pageList(ListDelayInstanceRequest request);

    /**
     * Delete
     *
     * @param request request
     * @return DeleteDelayVO
     */
    DeleteDelayInstanceVO delete(DeleteDelayInstanceRequest request);

    /**
     * Stop
     *
     * @param request request
     * @return StopDelayInstanceVO
     */
    StopDelayInstanceVO stop(StopDelayInstanceRequest request);

    /**
     * List log
     *
     * @param request request
     * @return ListDelayInstanceLogVO
     */
    ListDelayInstanceLogVO listProcessorLog(ListDelayInstanceLogRequest request);
}
