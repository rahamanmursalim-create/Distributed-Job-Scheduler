package io.distributedjobscheduler.server.admin.vo.part;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Schema(name = "PermItemVO", description = "AdminUserLoginVO PermItem")
public class PermItemVO {

    @Schema(description = "Perm name")
    private String name;

    @Schema(description = "Route path or API path")
    private String path;

    @Schema(description = "Extra meta data. JSON object: {title:some.name}")
    private MenuMetaVO meta;
}
