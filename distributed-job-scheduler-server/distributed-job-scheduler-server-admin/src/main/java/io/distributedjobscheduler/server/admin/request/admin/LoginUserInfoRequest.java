package io.distributedjobscheduler.server.admin.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Schema(name = "LoginUserInfoRequest", description = "LoginUserInfoRequest")
public class LoginUserInfoRequest {

    @Schema(description = "id")
    private Long id;
}
