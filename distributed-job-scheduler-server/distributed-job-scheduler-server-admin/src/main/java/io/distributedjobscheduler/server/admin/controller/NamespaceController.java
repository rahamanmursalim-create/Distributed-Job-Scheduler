package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.request.namespace.AddNamespaceRequest;
import io.distributedjobscheduler.server.admin.request.namespace.DeleteNamespaceRequest;
import io.distributedjobscheduler.server.admin.request.namespace.ListNamespaceRequest;
import io.distributedjobscheduler.server.admin.request.namespace.UpdateNamespaceRequest;
import io.distributedjobscheduler.server.admin.service.NamespaceService;
import io.distributedjobscheduler.server.admin.vo.namespace.AddNamespaceVO;
import io.distributedjobscheduler.server.admin.vo.namespace.DeleteNamespaceVO;
import io.distributedjobscheduler.server.admin.vo.namespace.ListNamespaceVO;
import io.distributedjobscheduler.server.admin.vo.namespace.UpdateNamespaceVO;
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
@Tag(name = "Namespace", description = "Namespace")
@RestController
@RequestMapping("/admin/namespace")
public class NamespaceController {
    private final NamespaceService namespaceService;

    @Autowired
    public NamespaceController(NamespaceService namespaceService) {
        this.namespaceService = namespaceService;
    }

    @Operation(summary = "Add namespace")
    @PostMapping("/add")
    public Result<AddNamespaceVO> add(@Valid @RequestBody AddNamespaceRequest addRequest) {
        return Result.success(this.namespaceService.add(addRequest));
    }

    @Operation(summary = "Update namespace")
    @PostMapping("/update")
    public Result<UpdateNamespaceVO> update(@Valid @RequestBody UpdateNamespaceRequest updateRequest) {
        return Result.success(this.namespaceService.update(updateRequest));
    }

    @Operation(summary = "Update namespace")
    @PostMapping("/delete")
    public Result<DeleteNamespaceVO> delete(@Valid @RequestBody DeleteNamespaceRequest deleteRequest) {
        return Result.success(this.namespaceService.delete(deleteRequest));
    }

    @Operation(summary = "List namespace")
    @GetMapping("/list")
    public Result<PageVO<ListNamespaceVO>> list(@Valid @ModelAttribute ListNamespaceRequest listRequest) {
        return Result.success(this.namespaceService.list(listRequest));
    }
}
