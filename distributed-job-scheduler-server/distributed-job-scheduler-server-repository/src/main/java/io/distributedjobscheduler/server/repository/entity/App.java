package io.distributedjobscheduler.server.repository.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "`app`")
public class App {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native",parameters = {@Parameter(name = "sequence_name", value = "app_id")})
    private Long id;

    /**
     * NamespaceId
     */
    @Column(name = "`namespace_id`")
    private Long namespaceId;

    /**
     * App name
     */
    @Column(name = "`name`")
    private String name;

    /**
     * App desc
     */
    @Column(name = "`desc`")
    private String desc;

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
}
