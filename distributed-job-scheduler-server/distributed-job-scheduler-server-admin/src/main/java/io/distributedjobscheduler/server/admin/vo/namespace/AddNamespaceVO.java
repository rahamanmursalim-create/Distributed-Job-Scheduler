package io.distributedjobscheduler.server.admin.vo.namespace;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@Schema()
public class AddNamespaceVO {

    @Schema(description = "New namespace primary id", required = true)
    private Long id;
}
