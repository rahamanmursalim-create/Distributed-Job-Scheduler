package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.system.SystemUpdateRequest;
import io.distributedjobscheduler.server.admin.service.SystemService;
import io.distributedjobscheduler.server.admin.vo.system.SystemUpdateVO;
import io.distributedjobscheduler.server.admin.vo.system.SystemVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * @author riki
 * @since 1.0.0
 */
@Tag(name = "System", description = "System")
@RestController
@RequestMapping("/admin/system")
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @Operation(summary = "Get the latest System information")
    @GetMapping("/config")
    public Result<SystemVO> getLatest() {
        return Result.success(this.systemService.getLatest());
    }

    @Operation(summary = "Update the System information")
    @PostMapping("/update")
    public Result<SystemUpdateVO> update(@Valid @RequestBody SystemUpdateRequest request) {
        return Result.success(this.systemService.update(request));
    }
}
