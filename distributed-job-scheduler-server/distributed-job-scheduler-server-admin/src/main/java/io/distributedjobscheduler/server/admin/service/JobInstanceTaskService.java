package io.distributedjobscheduler.server.admin.service;

import io.distributedjobscheduler.server.admin.request.task.ListChildTaskRequest;
import io.distributedjobscheduler.server.admin.request.task.ListTaskRequest;
import io.distributedjobscheduler.server.admin.request.task.ListTaskLogRequest;
import io.distributedjobscheduler.server.admin.request.task.StopTaskRequest;
import io.distributedjobscheduler.server.admin.vo.task.ListChildTaskVO;
import io.distributedjobscheduler.server.admin.vo.task.ListTaskVO;
import io.distributedjobscheduler.server.admin.vo.task.ListTaskLogVO;
import io.distributedjobscheduler.server.admin.vo.task.StopTaskVO;
import io.distributedjobscheduler.server.common.vo.PageVO;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.7
 */
public interface JobInstanceTaskService {

    /**
     * Get task list
     *
     * @param request request
     * @return PageVO
     */
    PageVO<ListTaskVO> getTaskList(ListTaskRequest request);


    /**
     * Get child list
     *
     * @param request request
     * @return PageVO
     */
    PageVO<ListChildTaskVO> getChildList(ListChildTaskRequest request);

    /**
     * Stop task
     *
     * @param request request
     * @return StopTaskVO
     */
    StopTaskVO stopTask(StopTaskRequest request);

    /**
     * Get task log list
     *
     * @param request request
     * @return ListTaskLogVO
     */
    ListTaskLogVO getTaskLogList(ListTaskLogRequest request);
}
