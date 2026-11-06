package io.distributedjobscheduler.server.admin.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import io.distributedjobscheduler.common.util.JsonUtil;
import io.distributedjobscheduler.server.admin.constant.CodeEnum;
import io.distributedjobscheduler.server.admin.request.alert.AddAlertRuleRequest;
import io.distributedjobscheduler.server.admin.request.alert.DeleteAlertRuleRequest;
import io.distributedjobscheduler.server.admin.request.alert.ListAlertRuleRequest;
import io.distributedjobscheduler.server.admin.request.alert.UpdateAlertRuleRequest;
import io.distributedjobscheduler.server.admin.request.alert.UpdateAlertRuleStatusRequest;
import io.distributedjobscheduler.server.admin.service.AlertRuleService;
import io.distributedjobscheduler.server.admin.vo.alert.AddAlertRuleVO;
import io.distributedjobscheduler.server.admin.vo.alert.DeleteAlertRuleVO;
import io.distributedjobscheduler.server.admin.vo.alert.ListAlertRuleVO;
import io.distributedjobscheduler.server.admin.vo.alert.UpdateAlertRuleStatusVO;
import io.distributedjobscheduler.server.admin.vo.alert.UpdateAlertRuleVO;
import io.distributedjobscheduler.server.cluster.data.RefreshData;
import io.distributedjobscheduler.server.common.dto.PageDTO;
import io.distributedjobscheduler.server.common.util.BeanMapperUtil;
import io.distributedjobscheduler.server.common.util.PageUtil;
import io.distributedjobscheduler.server.common.vo.PageVO;
import io.distributedjobscheduler.server.repository.constant.AlertMethodEnum;
import io.distributedjobscheduler.server.repository.dao.AlertRuleDAO;
import io.distributedjobscheduler.server.repository.entity.AlertRule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.5
 */
@Service
public class AlertRuleServiceImpl implements AlertRuleService {
    private final AlertRuleDAO alertRuleDAO;
    private final RefreshData refreshData;

    @Autowired
    public AlertRuleServiceImpl(AlertRuleDAO alertRuleDAO, RefreshData refreshData) {
        this.alertRuleDAO = alertRuleDAO;
        this.refreshData = refreshData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddAlertRuleVO add(AddAlertRuleRequest request) {
        // Check rule
        this.checkRule(request);

        AlertRule alertRule = BeanMapperUtil.map(request, AlertRule.class);
        alertRule.setNamespaceAppIds(JsonUtil.encode(request.getNamespaceAppIds()));
        alertRule.setEvents(JsonUtil.encode(request.getEvents()));
        alertRule.setMetrics(JsonUtil.encode(request.getMetrics()));

        // Save
        this.alertRuleDAO.save(alertRule);

        // Refresh cluster version.
        this.refreshData.refreshSystemClusterVersion();
        return new AddAlertRuleVO();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeleteAlertRuleVO delete(DeleteAlertRuleRequest request) {
        // Delete
        this.alertRuleDAO.deleteById(request.getId());

        // Refresh cluster version.
        this.refreshData.refreshSystemClusterVersion();
        return new DeleteAlertRuleVO();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UpdateAlertRuleVO update(UpdateAlertRuleRequest request) {
        // Check rule
        this.checkRule(request);

        AlertRule alertRule = BeanMapperUtil.map(request, AlertRule.class);
        alertRule.setNamespaceAppIds(JsonUtil.encode(request.getNamespaceAppIds()));
        alertRule.setEvents(JsonUtil.encode(request.getEvents()));
        alertRule.setMetrics(JsonUtil.encode(request.getMetrics()));

        // Update
        this.alertRuleDAO.update(alertRule);

        // Refresh cluster version.
        this.refreshData.refreshSystemClusterVersion();
        return new UpdateAlertRuleVO();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UpdateAlertRuleStatusVO updateStatus(UpdateAlertRuleStatusRequest request) {
        // Update status
        this.alertRuleDAO.updateStatus(request.getId(), request.getStatus());

        // Refresh cluster version.
        this.refreshData.refreshSystemClusterVersion();
        return new UpdateAlertRuleStatusVO();
    }

    @Override
    public PageVO<ListAlertRuleVO> list(ListAlertRuleRequest request) {
        PageDTO<AlertRule> pageList = this.alertRuleDAO.pageList(request.getName(), request.getPage(), request.getSize());
        if (CollectionUtils.isEmpty(pageList.getList())) {
            return PageUtil.emptyList(ListAlertRuleVO.class);
        }

        return PageUtil.convert(pageList, r -> {
            ListAlertRuleVO listAlertRuleVO = new ListAlertRuleVO();
            listAlertRuleVO.setId(r.getId());
            listAlertRuleVO.setName(r.getName());
            listAlertRuleVO.setLocale(r.getLocale());
            listAlertRuleVO.setMethod(r.getMethod());
            listAlertRuleVO.setUrl(r.getUrl());
            listAlertRuleVO.setSecret(r.getSecret());
            listAlertRuleVO.setStatus(r.getStatus());
            listAlertRuleVO.setCreateTime(r.getCreateTime());
            listAlertRuleVO.setUpdateTime(r.getUpdateTime());

            // App ids
            listAlertRuleVO.setNamespaceAppIds(r.getNamespaceAppIdsByJson());

            // Events
            listAlertRuleVO.setEvents(r.getEventsByJson());

            // Metrics
            listAlertRuleVO.setMetrics(JsonUtil.decode(r.getMetrics(), new TypeReference<List<ListAlertRuleVO.AlertRuleMetricsVO>>() {
            }));
            return listAlertRuleVO;
        });
    }

    private void checkRule(AddAlertRuleRequest request) {
        if (AlertMethodEnum.isWecom(request.getMethod())) {
            return;
        }

        CodeEnum.ALERT_SECRET_EMPTY.assertIsTrue(StringUtils.isNotBlank(request.getSecret()));
    }
}
