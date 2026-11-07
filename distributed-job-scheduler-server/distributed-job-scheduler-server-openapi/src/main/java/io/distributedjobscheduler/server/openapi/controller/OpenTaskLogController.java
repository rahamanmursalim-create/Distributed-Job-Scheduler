package io.distributedjobscheduler.server.openapi.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.openapi.request.WorkerJobInstanceTaskLogRequest;
import io.distributedjobscheduler.server.openapi.service.OpenTaskLogService;
import io.distributedjobscheduler.server.openapi.vo.WorkerJobInstanceTaskLogVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@RestController
@Tag(name = "Task-log", description = "Task-log")
@RequestMapping("/openapi/task-log")
public class OpenTaskLogController {

    private final OpenTaskLogService openTaskLogService;

    @Autowired
    public OpenTaskLogController(OpenTaskLogService openTaskLogService) {
        this.openTaskLogService = openTaskLogService;
    }

    /**
     * During the execution of a task on the client, there is periodic real-time batch reporting of task logs.
     *
     * @param logRequest log request.
     */
    @Operation(summary = "batch add")
    @PostMapping("/batch-add")
    public Result<WorkerJobInstanceTaskLogVO> batchAdd(WorkerJobInstanceTaskLogRequest logRequest) {
        return Result.success(this.openTaskLogService.batchAdd(logRequest));
    }
}
