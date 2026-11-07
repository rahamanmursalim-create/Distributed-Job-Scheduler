package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.delay.DeleteDelayInstanceRequest;
import io.distributedjobscheduler.server.admin.request.delay.DeleteDelayRequest;
import io.distributedjobscheduler.server.admin.request.delay.ListDelayInstanceLogRequest;
import io.distributedjobscheduler.server.admin.request.delay.ListDelayInstanceRequest;
import io.distributedjobscheduler.server.admin.request.delay.StopDelayInstanceRequest;
import io.distributedjobscheduler.server.admin.vo.delay.DeleteDelayInstanceVO;
import io.distributedjobscheduler.server.admin.service.DelayInstanceService;
import io.distributedjobscheduler.server.admin.vo.delay.ListDelayInstanceLogVO;
import io.distributedjobscheduler.server.admin.vo.delay.ListDelayInstanceVO;
import io.distributedjobscheduler.server.admin.vo.delay.StopDelayInstanceVO;
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
@Tag(name = "Delay Instance", description = "Delay Instance")
@RestController
@RequestMapping("/admin/delay-instance")
public class DelayInstanceController {

    private final DelayInstanceService delayInstanceService;

    @Autowired
    public DelayInstanceController(DelayInstanceService delayInstanceService) {
        this.delayInstanceService = delayInstanceService;
    }

    @Operation(summary = "List delay instance")
    @GetMapping("/list")
    public Result<PageVO<ListDelayInstanceVO>> list(@Valid @ModelAttribute ListDelayInstanceRequest request) {
        return Result.success(this.delayInstanceService.pageList(request));
    }

    @Operation(summary = "Delete delay instance")
    @PostMapping("/delete")
    public Result<DeleteDelayInstanceVO> delete(@Valid @RequestBody DeleteDelayInstanceRequest request) {
        return Result.success(this.delayInstanceService.delete(request));
    }

    @Operation(summary = "Delete delay instance")
    @PostMapping("/stop")
    public Result<StopDelayInstanceVO> stop(@Valid @RequestBody StopDelayInstanceRequest request) {
        return Result.success(this.delayInstanceService.stop(request));
    }

    @Operation(summary = "List delay instance log")
    @GetMapping("/list-processor-log")
    public Result<ListDelayInstanceLogVO> listProcessorLog(@Valid @ModelAttribute ListDelayInstanceLogRequest request) {
        return Result.success(this.delayInstanceService.listProcessorLog(request));
    }
}
