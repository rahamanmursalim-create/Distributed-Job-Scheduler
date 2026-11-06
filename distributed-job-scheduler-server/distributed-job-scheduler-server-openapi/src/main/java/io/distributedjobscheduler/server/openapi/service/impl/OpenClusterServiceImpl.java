package io.distributedjobscheduler.server.openapi.service.impl;

import io.distributedjobscheduler.common.context.Node;
import io.distributedjobscheduler.server.common.ClusterContext;
import io.distributedjobscheduler.server.openapi.request.ClusterOnlineRequest;
import io.distributedjobscheduler.server.openapi.service.OpenClusterService;
import io.distributedjobscheduler.server.openapi.vo.ClusterOnlineVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.3
 */
@Service
public class OpenClusterServiceImpl implements OpenClusterService {
    @Override
    public ClusterOnlineVO online(ClusterOnlineRequest clusterOnlineRequest) {
        ClusterOnlineVO clusterOnlineVO = new ClusterOnlineVO();
        List<String> servers = ClusterContext.getNodesMap().values().stream()
                .map(Node::getAkkaAddress)
                .collect(Collectors.toList());
        clusterOnlineVO.setServers(servers);
        return clusterOnlineVO;
    }
}
