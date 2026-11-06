package io.distributedjobscheduler.server.admin.vo.namespace;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
@Schema()
public class ListNamespaceVO {
    @Schema(description = "Namespace primary id", required = true)
    private Long id;

    @Schema(description = "Namespace name", required = true)
    private String name;

    @Schema(description = "Namespace uuid", required = true)
    private String uuid;

    @Schema(description = "Namespace createTime", required = true)
    private Long createTime;
}
