package io.distributedjobscheduler.server.admin.controller;

import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.server.admin.constant.AdminConstant;
import io.distributedjobscheduler.server.admin.request.admin.AdminUserLoginRequest;
import io.distributedjobscheduler.server.admin.request.admin.AdminUserLogoutRequest;
import io.distributedjobscheduler.server.admin.request.admin.LoginUserInfoRequest;
import io.distributedjobscheduler.server.admin.request.admin.UpdateUserPasswordRequest;
import io.distributedjobscheduler.server.admin.service.AdminLoginService;
import io.distributedjobscheduler.server.admin.vo.admin.AdminUserLoginVO;
import io.distributedjobscheduler.server.admin.vo.admin.AdminUserLogoutVO;
import io.distributedjobscheduler.server.admin.vo.admin.LoginUserInfoVO;
import io.distributedjobscheduler.server.admin.vo.admin.UpdateUserPasswordVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Objects;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Tag(name = "AdminLoginInOut", description = "AdminLogInOut")
@RestController
@RequestMapping("/admin/user")
public class AdminController {

    private final AdminLoginService adminLoginService;

    @Autowired
    public AdminController(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @Operation(summary = "User login")
    @PostMapping("/login")
    public Result<AdminUserLoginVO> login(@Valid @RequestBody AdminUserLoginRequest request) {
        return Result.success(this.adminLoginService.login(request));
    }

    /**
     * Login user info
     */
    @Operation(summary = "Login user")
    @GetMapping("/user-info")
    public Result<LoginUserInfoVO> userInfo(@Valid @ModelAttribute LoginUserInfoRequest loginUserInfoRequest, HttpServletRequest request) {
        if (Objects.isNull(loginUserInfoRequest.getId())) {
            loginUserInfoRequest.setId((Long) request.getAttribute(AdminConstant.REQUEST_UID_KEY));
        }

        return Result.success(this.adminLoginService.loginUserInfo(loginUserInfoRequest));
    }

    /**
     * Login user info
     */
    @Operation(summary = "Update password")
    @PostMapping("/update-password")
    public Result<UpdateUserPasswordVO> updatePassword(@Valid @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest) {
        return Result.success(this.adminLoginService.updatePassword(updateUserPasswordRequest));
    }

    /**
     * Login out
     */
    @Operation(summary = "User logout")
    @PostMapping("/logout")
    public Result<AdminUserLogoutVO> logout(@RequestBody(required = false) AdminUserLogoutRequest request, @RequestHeader HttpHeaders headers) {
        String sessKey = headers.getFirst(AdminConstant.HEADER_SESSION_KEY);

        return Result.success(this.adminLoginService.logout(request, sessKey));
    }

}

