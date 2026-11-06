package io.distributedjobscheduler.server.admin.service;

import io.distributedjobscheduler.server.repository.entity.AdminUser;

/**
 * @author inhere
 * @since 1.0.0
 */
public interface AdminUserService {

    /**
     * Get by session key.
     *
     * @param sessionKey session key.
     * @return AdminUserUpdateVO
     */
    AdminUser getBySessionKey(String sessionKey);
}
