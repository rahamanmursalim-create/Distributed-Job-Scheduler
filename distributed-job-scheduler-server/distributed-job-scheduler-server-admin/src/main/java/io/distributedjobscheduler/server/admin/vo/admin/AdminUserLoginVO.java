package io.distributedjobscheduler.server.admin.vo.admin;

import io.distributedjobscheduler.server.admin.vo.part.MenuItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Builder
@Schema(name = "AdminUserLoginVO", description = "AdminUserLoginVO")
public class AdminUserLoginVO {

    @Schema(description = "PK")
    private Long id;

    @Schema(description = "User name")
    private String username;

    @Schema(description = "Nickname")
    private String nickname;

    @Schema(description = "Session Key, please set to header \"session: sessionKey\"")
    private String sessionKey;

    @Schema(description = "user is supper admin")
    private Boolean supperAdmin;

    @Schema(description = "Permission names for user")
    private List<String> permNames;
}

