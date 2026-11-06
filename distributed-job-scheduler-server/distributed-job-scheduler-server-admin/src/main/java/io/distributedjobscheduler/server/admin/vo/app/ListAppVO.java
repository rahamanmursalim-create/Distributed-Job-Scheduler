package io.distributedjobscheduler.server.admin.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
@Schema()
public class ListAppVO {

    @Schema(description = "App primary id", required = true)
    private Long id;

    @Schema(description = "App namespace id", required = true)
    private Long namespaceId;

    @Schema(description = "App namespace name", required = true)
    private String namespaceName;

    @Schema(description = "App name", required = true)
    private String name;

    @Schema(description = "App desc", required = true)
    private String desc;

    @Schema(description = "App createTime", required = true)
    private Long createTime;
}
