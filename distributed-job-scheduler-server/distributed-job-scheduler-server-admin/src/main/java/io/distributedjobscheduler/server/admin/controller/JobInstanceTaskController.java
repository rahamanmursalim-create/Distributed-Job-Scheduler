package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.task.ListChildTaskRequest;
import io.distributedjobscheduler.server.admin.request.task.ListTaskLogRequest;
import io.distributedjobscheduler.server.admin.request.task.ListTaskRequest;
import io.distributedjobscheduler.server.admin.request.task.StopTaskRequest;
import io.distributedjobscheduler.server.admin.service.JobInstanceTaskService;
import io.distributedjobscheduler.server.admin.vo.task.ListChildTaskVO;
import io.distributedjobscheduler.server.admin.vo.task.ListTaskLogVO;
import io.distributedjobscheduler.server.admin.vo.task.ListTaskVO;
import io.distributedjobscheduler.server.admin.vo.task.StopTaskVO;
import io.distributedjobscheduler.server.common.vo.PageVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.7
 */
@Tag(name = "Instance task", description = "Instance task")
@RestController
@RequestMapping("/admin/instance-task")
public class JobInstanceTaskController {

    private final JobInstanceTaskService jobInstanceTaskService;

    @Autowired
    public JobInstanceTaskController(JobInstanceTaskService jobInstanceTaskService) {
        this.jobInstanceTaskService = jobInstanceTaskService;
    }

    @Operation(summary = "List task")
    @GetMapping("/list")
    public Result<PageVO<ListTaskVO>> listSecond(@Valid @ModelAttribute ListTaskRequest listTaskRequest) {
        return Result.success(this.jobInstanceTaskService.getTaskList(listTaskRequest));
    }

    @Operation(summary = "List child")
    @GetMapping("/list-child")
    public Result<PageVO<ListChildTaskVO>> listSecond(@Valid @ModelAttribute ListChildTaskRequest listChildTaskRequest) {
        return Result.success(this.jobInstanceTaskService.getChildList(listChildTaskRequest));
    }

    @Operation(summary = "Stop task")
    @PostMapping("/stop-task")
    public Result<StopTaskVO> stopTask(@Valid @RequestBody StopTaskRequest request) {
        return Result.success(this.jobInstanceTaskService.stopTask(request));
    }


    @Operation(summary = "List task log")
    @GetMapping("/list-task-log")
    public Result<ListTaskLogVO> listTaskLog(@Valid @ModelAttribute ListTaskLogRequest listTaskLogRequest) {
        return Result.success(this.jobInstanceTaskService.getTaskLogList(listTaskLogRequest));
    }
}
