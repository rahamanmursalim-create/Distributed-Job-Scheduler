package io.distributedjobscheduler.server.admin.vo.perm;

import io.distributedjobscheduler.server.admin.vo.part.MenuItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Schema(name = "AdminPermissionMenus", description = "Admin permission menus")
public class AdminPermissionMenusVO {

    @Schema(description = "menu list")
    private List<MenuItemVO> list;
}
