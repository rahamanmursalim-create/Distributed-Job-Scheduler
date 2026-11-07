package io.distributedjobscheduler.server.admin.vo.part;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */

@Data
@Schema(name = "MenuItemVO", description = "AdminUserLoginVO MenuItem")
public class MenuItemVO {

    /**
     * id
     */
    private Long id;

    @Schema(description = "Route path or API path")
    private String path;

    @Schema(description = "Menu name key(perm,i18n)")
    private String name;

    @Schema(description = "Page component path")
    private String component;

    @Schema(description = "Extra meta data. JSON object: {icon:xx,title:some.name}")
    private MenuMetaVO meta;

    @Schema(description = "sub menus")
    private List<MenuItemVO> children;
}
