package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.constant.AdminConstant;
import io.distributedjobscheduler.server.admin.request.perm.AdminPermissionMenusRequest;
import io.distributedjobscheduler.server.admin.service.AdminPermService;
import io.distributedjobscheduler.server.admin.vo.perm.AdminPermissionMenusVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * @author inhere
 * @since 1.0.0
 */
@Tag(name = "AdminPerm", description = "AdminPerm")
@RestController
@RequestMapping("/admin/perms")
public class PermController {

    private final AdminPermService adminPermService;

    @Autowired
    public PermController(AdminPermService adminPermService) {
        this.adminPermService = adminPermService;
    }

    @Operation(summary = "List menus")
    @GetMapping("/menus")
    public Result<AdminPermissionMenusVO> menus(@Valid @ModelAttribute AdminPermissionMenusRequest listRequest, HttpServletRequest request) {
        listRequest.setUid((Long) request.getAttribute(AdminConstant.REQUEST_UID_KEY));
        return Result.success(this.adminPermService.getMenus(listRequest));
    }
}

