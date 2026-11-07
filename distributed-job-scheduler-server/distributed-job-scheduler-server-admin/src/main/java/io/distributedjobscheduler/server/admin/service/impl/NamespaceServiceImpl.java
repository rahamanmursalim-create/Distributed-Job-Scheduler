package io.distributedjobscheduler.server.admin.service.impl;

import io.distributedjobscheduler.common.constant.CommonConstant;
import io.distributedjobscheduler.server.admin.constant.CodeEnum;
import io.distributedjobscheduler.server.admin.request.namespace.AddNamespaceRequest;
import io.distributedjobscheduler.server.admin.request.namespace.DeleteNamespaceRequest;
import io.distributedjobscheduler.server.admin.request.namespace.ListNamespaceRequest;
import io.distributedjobscheduler.server.admin.request.namespace.UpdateNamespaceRequest;
import io.distributedjobscheduler.server.admin.service.NamespaceService;
import io.distributedjobscheduler.server.admin.vo.namespace.AddNamespaceVO;
import io.distributedjobscheduler.server.admin.vo.namespace.DeleteNamespaceVO;
import io.distributedjobscheduler.server.admin.vo.namespace.ListNamespaceVO;
import io.distributedjobscheduler.server.admin.vo.namespace.UpdateNamespaceVO;
import io.distributedjobscheduler.server.common.dto.PageDTO;
import io.distributedjobscheduler.server.common.util.BeanMapperUtil;
import io.distributedjobscheduler.server.common.util.PageUtil;
import io.distributedjobscheduler.server.common.vo.PageVO;
import io.distributedjobscheduler.server.repository.dao.AppDAO;
import io.distributedjobscheduler.server.repository.dao.NamespaceDAO;
import io.distributedjobscheduler.server.repository.entity.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Service
public class NamespaceServiceImpl implements NamespaceService {
    private final NamespaceDAO namespaceDAO;
    private final AppDAO appDAO;

    @Autowired
    public NamespaceServiceImpl(NamespaceDAO namespaceDAO, AppDAO appDAO) {
        this.namespaceDAO = namespaceDAO;
        this.appDAO = appDAO;
    }

    @Override
    public AddNamespaceVO add(AddNamespaceRequest addRequest) {
        Namespace namespace = new Namespace();
        namespace.setName(addRequest.getName());
        namespace.setUuid(UUID.randomUUID().toString());

        // Save
        long id = this.namespaceDAO.save(namespace);
        return new AddNamespaceVO().setId(id);
    }

    @Override
    public UpdateNamespaceVO update(UpdateNamespaceRequest updateRequest) {
        this.namespaceDAO.update(BeanMapperUtil.map(updateRequest, Namespace.class));
        return new UpdateNamespaceVO();
    }

    @Override
    public DeleteNamespaceVO delete(DeleteNamespaceRequest deleteNamespaceRequest) {
        if (Objects.nonNull(this.appDAO.getFirstByNamespaceId(deleteNamespaceRequest.getId()))) {
            CodeEnum.NAMESPACE_DELETE_INVALID.throwException();
        }

        Namespace namespace = BeanMapperUtil.map(deleteNamespaceRequest, Namespace.class);
        namespace.setDeleted(CommonConstant.YES);
        this.namespaceDAO.update(namespace);
        return new DeleteNamespaceVO();
    }

    @Override
    public PageVO<ListNamespaceVO> list(ListNamespaceRequest listRequest) {
        PageDTO<Namespace> pageDTO = this.namespaceDAO.pageList(listRequest.getName(), listRequest.getPage(), listRequest.getSize());
        return PageUtil.convert(pageDTO, n -> BeanMapperUtil.map(n, ListNamespaceVO.class));
    }
}
