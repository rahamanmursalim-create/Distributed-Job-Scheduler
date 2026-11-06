package io.distributedjobscheduler.server.admin.service;

import io.distributedjobscheduler.server.admin.request.namespace.AddNamespaceRequest;
import io.distributedjobscheduler.server.admin.request.namespace.DeleteNamespaceRequest;
import io.distributedjobscheduler.server.admin.request.namespace.ListNamespaceRequest;
import io.distributedjobscheduler.server.admin.request.namespace.UpdateNamespaceRequest;
import io.distributedjobscheduler.server.admin.vo.namespace.AddNamespaceVO;
import io.distributedjobscheduler.server.admin.vo.namespace.DeleteNamespaceVO;
import io.distributedjobscheduler.server.admin.vo.namespace.ListNamespaceVO;
import io.distributedjobscheduler.server.admin.vo.namespace.UpdateNamespaceVO;
import io.distributedjobscheduler.server.common.vo.PageVO;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
public interface NamespaceService {

    /**
     * Add namespace.
     *
     * @param addRequest AddNamespaceRequest
     * @return AddNamespaceResponse
     */
    AddNamespaceVO add(AddNamespaceRequest addRequest);

    /**
     * Add namespace.
     *
     * @param updateRequest UpdateNamespaceRequest
     * @return UpdateNamespaceResponse
     */
    UpdateNamespaceVO update(UpdateNamespaceRequest updateRequest);

    /**
     * Delete namespace
     *
     * @param deleteNamespaceRequest deleteNamespaceRequest
     * @return UpdateNamespaceVO
     */
    DeleteNamespaceVO delete(DeleteNamespaceRequest deleteNamespaceRequest);

    /**
     * Add namespace.
     *
     * @param listRequest ListNamespaceRequest
     * @return ListNamespaceResponse
     */
    PageVO<ListNamespaceVO> list(ListNamespaceRequest listRequest);
}
