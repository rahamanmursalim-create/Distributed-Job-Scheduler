package io.distributedjobscheduler.server.admin.vo.job;

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
public class AddJobVO {

    @Schema(description = "New job primary id", required = true)
    private Long id;
}
