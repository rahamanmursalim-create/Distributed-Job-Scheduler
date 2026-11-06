package io.distributedjobscheduler.server.admin.vo.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author inhere
 * @since 1.0.0
 */
@Data
@Schema(name = "LoginUserInfoVO", description = "LoginUserInfoVO")
public class LoginUserInfoVO {

    @Schema(description = "PK")
    private Long id;

    @Schema(description = "Username", required = true)
    private String username;

    @Schema(description = "Nickname", required = true)
    private String nickname;

    @Schema(description = "Login ip", required = true)
    private String loginIp;

    @Schema(description = "Login time", required = true)
    private String loginTime;

    @Schema(description = "Token", required = true)
    private String token;

    @Schema(description = "Create time", required = true)
    private Long createTime;
}

