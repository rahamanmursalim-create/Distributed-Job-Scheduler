package io.distributedjobscheduler.server.admin.service;

import io.distributedjobscheduler.server.admin.request.perm.AdminPermissionMenusRequest;
import io.distributedjobscheduler.server.admin.vo.perm.AdminPermissionMenusVO;

/**
 * @author inhere
 * @since 1.0.0
 */
public interface AdminPermService {

    /**
     * Get permission menus.
     *
     * @param request request
     * @return AdminPermissionMenusVO
     */
    AdminPermissionMenusVO getMenus(AdminPermissionMenusRequest request);
}
