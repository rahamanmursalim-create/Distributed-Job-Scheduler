package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.server.JobSlotRequest;
import io.distributedjobscheduler.server.admin.request.server.ServerListRequest;
import io.distributedjobscheduler.server.admin.service.ServerService;
import io.distributedjobscheduler.server.admin.vo.server.JobSlotListVO;
import io.distributedjobscheduler.server.admin.vo.server.ServerListVO;
import io.distributedjobscheduler.server.common.vo.PageVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * @author riki
 * @since 1.0.0
 */
@Tag(name = "Server", description = "Server")
@RestController
@RequestMapping("/admin/server")
public class ServerController {

    private final ServerService serverService;

    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }


    @Operation(summary = "List Server by page")
    @GetMapping("/list-server")
    public Result<PageVO<ServerListVO>> listServer(@Valid @ModelAttribute ServerListRequest request) {
        return Result.success(this.serverService.getServerList(request));
    }

    @Operation(summary = "List job slot by page")
    @GetMapping("/list-job-slots")
    public Result<PageVO<JobSlotListVO>> listJobSlots(@Valid @ModelAttribute JobSlotRequest request) {
        return Result.success(this.serverService.getJobSlotsList(request));
    }
}
