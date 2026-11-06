package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.job.DeleteJobInstanceRequest;
import io.distributedjobscheduler.server.admin.request.job.ListJobInstanceRequest;
import io.distributedjobscheduler.server.admin.request.job.ListProcessorLogRequest;
import io.distributedjobscheduler.server.admin.request.job.StopJobInstanceRequest;
import io.distributedjobscheduler.server.admin.service.JobInstanceService;
import io.distributedjobscheduler.server.admin.vo.job.DeleteJobInstanceVO;
import io.distributedjobscheduler.server.admin.vo.job.ListJobInstanceVO;
import io.distributedjobscheduler.server.admin.vo.job.ListProcessorLogVO;
import io.distributedjobscheduler.server.admin.vo.job.StopJobInstanceVO;
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
 * @since 1.0.0
 */
@Tag(name = "JobInstance", description = "JobInstance")
@RestController
@RequestMapping("/admin/job-instance")
public class JobInstanceController {
    private final JobInstanceService jobInstanceService;

    @Autowired
    public JobInstanceController(JobInstanceService jobInstanceService) {
        this.jobInstanceService = jobInstanceService;
    }

    @Operation(summary = "List job instances")
    @GetMapping("/list")
    public Result<PageVO<ListJobInstanceVO>> listJobInstance(@Valid @ModelAttribute ListJobInstanceRequest listJobRequest) {
        return Result.success(this.jobInstanceService.getPageList(listJobRequest));
    }

    @Operation(summary = "Stop job instances")
    @PostMapping("/stop")
    public Result<StopJobInstanceVO> stopJobInstance(@Valid @RequestBody StopJobInstanceRequest stopRequest) {
        return Result.success(this.jobInstanceService.stopInstance(stopRequest));
    }

    @Operation(summary = "Delete job instances")
    @PostMapping("/delete")
    public Result<DeleteJobInstanceVO> stopJobInstance(@Valid @RequestBody DeleteJobInstanceRequest request) {
        return Result.success(this.jobInstanceService.deleteInstance(request));
    }

    @Operation(summary = "List job instance log")
    @GetMapping("/list-processor-log")
    public Result<ListProcessorLogVO> listProcessorLog(@Valid @ModelAttribute ListProcessorLogRequest request) {
        return Result.success(this.jobInstanceService.getProcessorList(request));
    }
}
