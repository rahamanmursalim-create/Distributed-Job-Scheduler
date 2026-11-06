package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.home.DelayChartRequest;
import io.distributedjobscheduler.server.admin.request.home.JobChartRequest;
import io.distributedjobscheduler.server.admin.request.home.SystemDataRequest;
import io.distributedjobscheduler.server.admin.request.home.TaskDataRequest;
import io.distributedjobscheduler.server.admin.service.HomeService;
import io.distributedjobscheduler.server.admin.vo.home.DelayChartVO;
import io.distributedjobscheduler.server.admin.vo.home.JobChartVO;
import io.distributedjobscheduler.server.admin.vo.home.SystemDataVO;
import io.distributedjobscheduler.server.admin.vo.home.TaskDataVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.2
 */
@Tag(name = "Home", description = "Home")
@RestController
@RequestMapping("/admin/home")
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @Operation(summary = "One line data")
    @GetMapping("/task-data")
    public Result<TaskDataVO> taskData(@Valid @ModelAttribute TaskDataRequest taskDataRequest) {
        return Result.success(this.homeService.taskData(taskDataRequest));
    }

    @Operation(summary = "Two line data")
    @GetMapping("/system-data")
    public Result<SystemDataVO> systemData(@Valid @ModelAttribute SystemDataRequest systemDataRequest) {
        return Result.success(this.homeService.systemData(systemDataRequest));
    }

    @Operation(summary = "Job chart")
    @GetMapping("/job-chart")
    public Result<JobChartVO> jobChart(@Valid @ModelAttribute JobChartRequest jobChartRequest) {
        return Result.success(this.homeService.jobChart(jobChartRequest));
    }

    @Operation(summary = "Delay chart")
    @GetMapping("/delay-chart")
    public Result<DelayChartVO> delayChart(@Valid @ModelAttribute DelayChartRequest delayChartRequest) {
        return Result.success(this.homeService.delayChart(delayChartRequest));
    }
}
