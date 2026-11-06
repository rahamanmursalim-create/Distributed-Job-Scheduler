package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.worker.WorkerListRequest;
import io.distributedjobscheduler.server.admin.service.WorkerService;
import io.distributedjobscheduler.server.admin.vo.worker.WorkerListVO;
import io.distributedjobscheduler.server.common.vo.PageVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author riki
 * @since 1.0.0
 */
@Tag(name = "Worker", description = "Worker")
@RestController
@RequestMapping("/admin/worker")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @Operation(summary = "List Worker by page")
    @GetMapping("/list")
    public Result<PageVO<WorkerListVO>> list(@ModelAttribute WorkerListRequest request) {
        return Result.success(workerService.getPage(request));
    }
}
