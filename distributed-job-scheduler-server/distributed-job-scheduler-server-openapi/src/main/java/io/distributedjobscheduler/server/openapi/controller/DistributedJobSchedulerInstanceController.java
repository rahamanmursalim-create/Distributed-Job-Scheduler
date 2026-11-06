package io.distributedjobscheduler.server.openapi.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.openapi.request.WorkerJobInstanceStatusRequest;
import io.distributedjobscheduler.server.openapi.request.WorkerJobInstanceTaskBatchRequest;
import io.distributedjobscheduler.server.openapi.service.DistributedJobSchedulerInstanceService;
import io.distributedjobscheduler.server.openapi.vo.WorkerJobInstanceStatusVO;
import io.distributedjobscheduler.server.openapi.vo.WorkerJobInstanceTaskBatchVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenghongyang sakuraovq@gmail.com
 * @since 1.0.0
 */
@RestController
@Tag(name = "Job-instance", description = "Job-instance")
@RequestMapping("/openapi/job-instance")
@Slf4j
public class DistributedJobSchedulerInstanceController {

    private final DistributedJobSchedulerInstanceService distributedJobSchedulerInstanceService;

    @Autowired
    public DistributedJobSchedulerInstanceController(DistributedJobSchedulerInstanceService distributedJobSchedulerInstanceService) {
        this.distributedJobSchedulerInstanceService = distributedJobSchedulerInstanceService;
    }

    @Operation(summary = "Handle status")
    @PostMapping("/handle-status")
    public Result<WorkerJobInstanceStatusVO> handleStatus(WorkerJobInstanceStatusRequest statusRequest) {
        return Result.success(this.distributedJobSchedulerInstanceService.handleStatus(statusRequest));
    }

    @Operation(summary = "Handle tasks")
    @PostMapping("/handle-tasks")
    public Result<WorkerJobInstanceTaskBatchVO> handleTasks(WorkerJobInstanceTaskBatchRequest batchRequest) {
        return Result.success(this.distributedJobSchedulerInstanceService.handleTasks(batchRequest));
    }
}
