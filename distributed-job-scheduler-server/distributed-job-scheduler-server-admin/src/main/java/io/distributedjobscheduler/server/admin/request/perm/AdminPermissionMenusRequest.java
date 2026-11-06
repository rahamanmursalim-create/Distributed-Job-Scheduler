package io.distributedjobscheduler.server.admin.request.perm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
public class AdminPermissionMenusRequest {

    @Schema(description = "Admin uid", hidden = true)
    private Long uid;
}
