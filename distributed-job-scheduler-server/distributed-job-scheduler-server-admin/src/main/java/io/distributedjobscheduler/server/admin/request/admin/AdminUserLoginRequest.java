package io.distributedjobscheduler.server.admin.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author inhere
 * @since 1.0.0
 */
@Data
@Schema(name = "AdminUserLoginRequest", description = "AdminUserLoginRequest")
public class AdminUserLoginRequest {

    @NotBlank
    @Size(min = 2)
    @Schema(description = "User name")
    private String username;

    @NotBlank
    @Size(min = 6)
    @Schema(description = "Password, md5 hashed before submit")
    private String password;

    @NotNull
    @Schema(description = "Keep Login, if false will expired on after 30min")
    private Boolean keepLogin = false;

}

