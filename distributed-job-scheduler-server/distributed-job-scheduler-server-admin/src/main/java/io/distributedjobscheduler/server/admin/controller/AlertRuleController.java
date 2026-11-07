package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.alert.AddAlertRuleRequest;
import io.distributedjobscheduler.server.admin.request.alert.DeleteAlertRuleRequest;
import io.distributedjobscheduler.server.admin.request.alert.ListAlertRuleRequest;
import io.distributedjobscheduler.server.admin.request.alert.UpdateAlertRuleRequest;
import io.distributedjobscheduler.server.admin.request.alert.UpdateAlertRuleStatusRequest;
import io.distributedjobscheduler.server.admin.service.AlertRuleService;
import io.distributedjobscheduler.server.admin.vo.alert.AddAlertRuleVO;
import io.distributedjobscheduler.server.admin.vo.alert.DeleteAlertRuleVO;
import io.distributedjobscheduler.server.admin.vo.alert.ListAlertRuleVO;
import io.distributedjobscheduler.server.admin.vo.alert.UpdateAlertRuleStatusVO;
import io.distributedjobscheduler.server.admin.vo.alert.UpdateAlertRuleVO;
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
 * @since 1.0.5
 */
@Tag(name = "AlertRule", description = "Alert Rule")
@RestController
@RequestMapping("/admin/alert-rule")
public class AlertRuleController {

    private final AlertRuleService alertRuleService;

    @Autowired
    public AlertRuleController(AlertRuleService alertRuleService) {
        this.alertRuleService = alertRuleService;
    }

    @Operation(summary = "Add")
    @PostMapping("/add")
    public Result<AddAlertRuleVO> add(@Valid @RequestBody AddAlertRuleRequest request) {
        return Result.success(this.alertRuleService.add(request));
    }

    @Operation(summary = "Delete")
    @PostMapping("/delete")
    public Result<DeleteAlertRuleVO> delete(@Valid @RequestBody DeleteAlertRuleRequest request) {
        return Result.success(this.alertRuleService.delete(request));
    }

    @Operation(summary = "Update")
    @PostMapping("/update")
    public Result<UpdateAlertRuleVO> update(@Valid @RequestBody UpdateAlertRuleRequest request) {
        return Result.success(this.alertRuleService.update(request));
    }

    @Operation(summary = "Update status")
    @PostMapping("/update-status")
    public Result<UpdateAlertRuleStatusVO> updateStatus(@Valid @RequestBody UpdateAlertRuleStatusRequest request) {
        return Result.success(this.alertRuleService.updateStatus(request));
    }

    @Operation(summary = "List")
    @GetMapping("/list")
    public Result<PageVO<ListAlertRuleVO>> list(@Valid @ModelAttribute ListAlertRuleRequest request) {
        return Result.success(this.alertRuleService.list(request));
    }
}
