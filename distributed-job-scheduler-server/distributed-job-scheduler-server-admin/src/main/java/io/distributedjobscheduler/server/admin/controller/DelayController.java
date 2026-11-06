package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.delay.AddDelayRequest;
import io.distributedjobscheduler.server.admin.request.delay.DeleteDelayRequest;
import io.distributedjobscheduler.server.admin.request.delay.ListDelayRequest;
import io.distributedjobscheduler.server.admin.request.delay.UpdateDelayRequest;
import io.distributedjobscheduler.server.admin.service.DelayService;
import io.distributedjobscheduler.server.admin.vo.delay.AddDelayVO;
import io.distributedjobscheduler.server.admin.vo.delay.DeleteDelayVO;
import io.distributedjobscheduler.server.admin.vo.delay.ListDelayVO;
import io.distributedjobscheduler.server.admin.vo.delay.UpdateDelayVO;
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
@Tag(name = "Delay", description = "Delay")
@RestController
@RequestMapping("/admin/delay")
public class DelayController {
    private final DelayService delayService;

    @Autowired
    public DelayController(DelayService delayService) {
        this.delayService = delayService;
    }

    @Operation(summary = "Add delay")
    @PostMapping("/add")
    public Result<AddDelayVO> add(@Valid @RequestBody AddDelayRequest addRequest) {
        return Result.success(this.delayService.add(addRequest));
    }

    @Operation(summary = "List delay")
    @GetMapping("/list")
    public Result<PageVO<ListDelayVO>> list(@Valid @ModelAttribute ListDelayRequest listDelayRequest) {
        return Result.success(this.delayService.list(listDelayRequest));
    }

    @Operation(summary = "Delete delay")
    @PostMapping("/delete")
    public Result<DeleteDelayVO> delete(@Valid @RequestBody DeleteDelayRequest deleteDelayRequest) {
        return Result.success(this.delayService.delete(deleteDelayRequest));
    }

    @Operation(summary = "Update delay")
    @PostMapping("/update")
    public Result<UpdateDelayVO> update(@Valid @RequestBody UpdateDelayRequest updateDelayRequest) {
        return Result.success(this.delayService.update(updateDelayRequest));
    }
}
