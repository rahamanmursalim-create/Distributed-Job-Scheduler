package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.job.AddJobRequest;
import io.distributedjobscheduler.server.admin.request.job.DeleteJobRequest;
import io.distributedjobscheduler.server.admin.request.job.ExecuteJobRequest;
import io.distributedjobscheduler.server.admin.request.job.ListJobRequest;
import io.distributedjobscheduler.server.admin.request.job.TimeExpressionRequest;
import io.distributedjobscheduler.server.admin.request.job.UpdateJobRequest;
import io.distributedjobscheduler.server.admin.request.job.UpdateJobStatusRequest;
import io.distributedjobscheduler.server.admin.service.JobService;
import io.distributedjobscheduler.server.admin.vo.job.AddJobVO;
import io.distributedjobscheduler.server.admin.vo.job.DeleteJobVO;
import io.distributedjobscheduler.server.admin.vo.job.ExecuteJobVO;
import io.distributedjobscheduler.server.admin.vo.job.ListJobVO;
import io.distributedjobscheduler.server.admin.vo.job.TimeExpressionVO;
import io.distributedjobscheduler.server.admin.vo.job.UpdateJobStatusVO;
import io.distributedjobscheduler.server.admin.vo.job.UpdateJobVO;
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
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Tag(name = "Job", description = "Job")
@RestController
@RequestMapping("/admin/job")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @Operation(summary = "Add job")
    @PostMapping("/add")
    public Result<AddJobVO> add(@Valid @RequestBody AddJobRequest addJobRequest) {
        return Result.success(this.jobService.add(addJobRequest));
    }

    @Operation(summary = "Update job")
    @PostMapping("/update")
    public Result<UpdateJobVO> update(@Valid @RequestBody UpdateJobRequest updateJobRequest) {
        return Result.success(this.jobService.update(updateJobRequest));
    }

    @Operation(summary = "Update job status")
    @PostMapping("/update-status")
    public Result<UpdateJobStatusVO> updateStatus(@Valid @RequestBody UpdateJobStatusRequest updateJobStatusRequest) {
        return Result.success(this.jobService.updateStatus(updateJobStatusRequest));
    }

    @Operation(summary = "Delete job")
    @PostMapping("/delete")
    public Result<DeleteJobVO> delete(@Valid @RequestBody DeleteJobRequest deleteJobRequest) {
        return Result.success(this.jobService.delete(deleteJobRequest));
    }

    @Operation(summary = "Execute job")
    @PostMapping("/execute")
    public Result<ExecuteJobVO> execute(@Valid @RequestBody ExecuteJobRequest request) {
        return Result.success(this.jobService.execute(request));
    }

    @Operation(summary = "Execute job")
    @PostMapping("/time-expression")
    public Result<TimeExpressionVO> timeExpression(@Valid @RequestBody TimeExpressionRequest request) {
        return Result.success(this.jobService.timeExpression(request));
    }

    @Operation(summary = "List jobs")
    @GetMapping("/list")
    public Result<PageVO<ListJobVO>> listJob(@Valid @ModelAttribute ListJobRequest listJobRequest) {
        return Result.success(this.jobService.getPageList(listJobRequest));
    }
}
