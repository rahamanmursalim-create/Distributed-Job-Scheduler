package io.distributedjobscheduler.server.admin.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
@Data
public class UpdateUserPasswordRequest {
    @NotNull
    @Schema(description = "nickname")
    private Long id;

    @NotNull
    @Schema(description = "nickname")
    private String nickname;

    @NotNull
    @Schema(description = "password")
    private String password;

    @NotNull
    @Schema(description = "token")
    private String token;
}
