package io.distributedjobscheduler.server.openapi.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.openapi.request.DelayInstanceAddRequest;
import io.distributedjobscheduler.server.openapi.service.OpenDelayInstanceService;
import io.distributedjobscheduler.server.openapi.vo.DelayInstanceAddVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@RestController
@Tag(name = "Delay-instance", description = "Delay-instance")
@RequestMapping("/openapi/delay-instance")
public class OpenDelayInstanceController {

    private final OpenDelayInstanceService instanceService;

    @Autowired
    public OpenDelayInstanceController(OpenDelayInstanceService instanceService) {
        this.instanceService = instanceService;
    }

    @Operation(summary = "Add delay instance")
    @PostMapping("/add")
    public Result<DelayInstanceAddVO> add(@Valid @RequestBody DelayInstanceAddRequest addRequest) {
        return Result.success(this.instanceService.add(addRequest));
    }
}
