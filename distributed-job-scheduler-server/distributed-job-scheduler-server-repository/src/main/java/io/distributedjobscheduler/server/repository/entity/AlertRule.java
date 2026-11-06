package io.distributedjobscheduler.server.repository.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import io.distributedjobscheduler.common.util.JsonUtil;
import io.distributedjobscheduler.server.repository.constant.AlertMethodEnum;
import io.distributedjobscheduler.server.repository.constant.AlertRuleStatusEnum;
import lombok.CustomLog;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.5
 */
@Data
@Entity
@Table(name = "`alert_rule`")
public class AlertRule {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native", parameters = {@Parameter(name = "sequence_name", value = "alert_rule_id")})
    private Long id;

    /**
     * Rule name
     */
    @Column(name = "`name`")
    private String name;

    /**
     * Namespace application ids
     */
    @Column(name = "`namespace_app_ids`")
    private String namespaceAppIds;

    /**
     * Events
     */
    @Column(name = "`events`")
    private String events;

    /**
     * Metrics
     */
    @Column(name = "`metrics`")
    private String metrics;

    /**
     * Locale
     */
    @Column(name = "`locale`")
    private String locale;

    /**
     * Method
     *
     * @see AlertMethodEnum#getType()
     */
    @Column(name = "`method`")
    private String method;

    /**
     * Url
     */
    @Column(name = "`url`")
    private String url;

    /**
     * Secret
     */
    @Column(name = "`secret`")
    private String secret;

    /**
     * Status
     *
     * @see AlertRuleStatusEnum#getStatus()
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * Delete status. 1=yes 2=no
     */
    @Column(name = "`deleted`")
    private Integer deleted;

    /**
     * Delete time
     */
    @Column(name = "`delete_time`")
    private Long deleteTime;

    /**
     * Create time
     */
    @Column(name = "`create_time`")
    private Long createTime;

    /**
     * Update time
     */
    @Column(name = "`update_time`")
    private Long updateTime;

    public List<Long> getNamespaceAppIdsByJson() {
        return JsonUtil.decode(this.namespaceAppIds, new TypeReference<List<Long>>() {
        });
    }

    public List<String> getEventsByJson() {
        return JsonUtil.decode(this.getEvents(), new TypeReference<List<String>>() {
        });
    }
}
