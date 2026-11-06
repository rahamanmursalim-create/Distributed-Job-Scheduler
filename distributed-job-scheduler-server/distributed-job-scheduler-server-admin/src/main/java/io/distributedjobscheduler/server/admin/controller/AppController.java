package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.app.AddAppRequest;
import io.distributedjobscheduler.server.admin.request.app.DeleteAppRequest;
import io.distributedjobscheduler.server.admin.request.app.ListAppRequest;
import io.distributedjobscheduler.server.admin.request.app.UpdateAppRequest;
import io.distributedjobscheduler.server.admin.service.AppService;
import io.distributedjobscheduler.server.admin.vo.app.AddAppVO;
import io.distributedjobscheduler.server.admin.vo.app.DeleteAppVO;
import io.distributedjobscheduler.server.admin.vo.app.ListAppVO;
import io.distributedjobscheduler.server.admin.vo.app.UpdateAppVO;
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
@Tag(name = "App", description = "App")
@RestController
@RequestMapping("/admin/app")
public class AppController {
    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @Operation(summary = "Add application")
    @PostMapping("/add")
    public Result<AddAppVO> add(@Valid @RequestBody AddAppRequest addRequest) {
        return Result.success(this.appService.add(addRequest));
    }

    @Operation(summary = "Update application")
    @PostMapping("/update")
    public Result<UpdateAppVO> update(@Valid @RequestBody UpdateAppRequest updateRequest) {
        return Result.success(this.appService.update(updateRequest));
    }

    @Operation(summary = "Delete application")
    @PostMapping("/delete")
    public Result<DeleteAppVO> delete(@Valid @RequestBody DeleteAppRequest deleteAppRequest) {
        return Result.success(this.appService.delete(deleteAppRequest));
    }

    @Operation(summary = "List application")
    @GetMapping("/list")
    public Result<PageVO<ListAppVO>> list(@Valid @ModelAttribute ListAppRequest listRequest) {
        return Result.success(this.appService.list(listRequest));
    }
}
