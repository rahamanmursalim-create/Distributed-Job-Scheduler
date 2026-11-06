package io.distributedjobscheduler.server.openapi.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.openapi.request.ClusterOnlineRequest;
import io.distributedjobscheduler.server.openapi.service.OpenClusterService;
import io.distributedjobscheduler.server.openapi.vo.ClusterOnlineVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.3
 */
@RestController
@Tag(name = "Cluster", description = "Cluster")
@RequestMapping("/openapi/cluster")
public class OpenClusterController {
    private final OpenClusterService openClusterService;

    @Autowired
    public OpenClusterController(OpenClusterService openClusterService) {
        this.openClusterService = openClusterService;
    }

    @GetMapping("/online")
    public Result<ClusterOnlineVO> add(@Valid @ModelAttribute ClusterOnlineRequest clusterOnlineRequest) {
        return Result.success(this.openClusterService.online(clusterOnlineRequest));
    }
}
