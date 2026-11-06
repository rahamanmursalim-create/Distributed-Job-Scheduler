package io.distributedjobscheduler.server.openapi.service;

import io.distributedjobscheduler.server.openapi.request.ClusterOnlineRequest;
import io.distributedjobscheduler.server.openapi.vo.ClusterOnlineVO;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.3
 */
public interface OpenClusterService {

    /**
     * online
     *
     * @param clusterOnlineRequest clusterOnlineRequest
     * @return ClusterOnlineVO
     */
    ClusterOnlineVO online(ClusterOnlineRequest clusterOnlineRequest);
}
