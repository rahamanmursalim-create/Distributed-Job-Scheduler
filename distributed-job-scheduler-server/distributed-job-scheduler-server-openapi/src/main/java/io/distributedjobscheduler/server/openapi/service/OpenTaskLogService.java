package io.distributedjobscheduler.server.openapi.service;

import io.distributedjobscheduler.server.openapi.request.WorkerJobInstanceTaskLogRequest;
import io.distributedjobscheduler.server.openapi.vo.WorkerJobInstanceTaskLogVO;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.8
 */
public interface OpenTaskLogService {

    /**
     * Batch add
     *
     * @param logRequest logRequest
     * @return WorkerJobInstanceTaskLogVO
     */
    WorkerJobInstanceTaskLogVO batchAdd(WorkerJobInstanceTaskLogRequest logRequest);
}
