package io.distributedjobscheduler.server.openapi.service;

import io.distributedjobscheduler.server.openapi.request.DelayInstanceAddRequest;
import io.distributedjobscheduler.server.openapi.vo.DelayInstanceAddVO;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public interface OpenDelayInstanceService {

    /**
     * Add delay.
     *
     * @param addRequest addRequest
     * @return DelayInstanceAddVO
     */
    DelayInstanceAddVO add(DelayInstanceAddRequest addRequest);
}
