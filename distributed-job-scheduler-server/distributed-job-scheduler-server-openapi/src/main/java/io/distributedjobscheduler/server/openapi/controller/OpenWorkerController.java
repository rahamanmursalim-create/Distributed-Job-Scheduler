package io.distributedjobscheduler.server.openapi.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.openapi.request.WorkerHeartbeatRequest;
import io.distributedjobscheduler.server.openapi.request.WorkerStartRequest;
import io.distributedjobscheduler.server.openapi.request.WorkerStopRequest;
import io.distributedjobscheduler.server.openapi.service.OpenWorkerService;
import io.distributedjobscheduler.server.openapi.vo.ServerHeartbeatVO;
import io.distributedjobscheduler.server.openapi.vo.ServerWorkerStartVO;
import io.distributedjobscheduler.server.openapi.vo.ServerWorkerStopVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
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
@Tag(name = "worker", description = "worker")
@RequestMapping("/openapi/worker")
@Slf4j
public class OpenWorkerController {

    private final OpenWorkerService openWorkerService;

    public OpenWorkerController(OpenWorkerService openWorkerService) {
        this.openWorkerService = openWorkerService;
    }

    /**
     * Client needs to report client information every time it starts.
     *
     * @param workerStartRequest worker start request
     * @return Result
     */
    @Operation(summary = "Worker start")
    @PostMapping("/start")
    public Result<ServerWorkerStartVO> workerStart(@Valid @RequestBody WorkerStartRequest workerStartRequest) {
        ServerWorkerStartVO result = this.openWorkerService.workerStart(workerStartRequest);
        log.info("Worker register success! address={}", workerStartRequest.getAddress());
        return Result.success(result);
    }

    /**
     * Client needs to notify the server every time it goes offline.
     *
     * @param workerStopRequest worker stop request
     * @return Result
     */
    @Operation(summary = "Worker stop")
    @PostMapping("/stop")
    public Result<ServerWorkerStopVO> workerStop(@Valid @RequestBody WorkerStopRequest workerStopRequest) {
        ServerWorkerStopVO result = this.openWorkerService.workerStop(workerStopRequest);
        log.info("Worker stop success! address={}", workerStopRequest.getAddress());
        return Result.success(result);
    }

    @Operation(summary = "worker heartbeat")
    @PostMapping("/heartbeat")
    public Result<ServerHeartbeatVO> heartbeat(@Valid @RequestBody WorkerHeartbeatRequest workerHeartbeatRequest) {
        ServerHeartbeatVO result = this.openWorkerService.workerHeartbeat(workerHeartbeatRequest);
        return Result.success(result);
    }
}
