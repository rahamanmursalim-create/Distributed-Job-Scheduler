package io.distributedjobscheduler.server.repository.entity;

import lombok.Data;
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
@Table(name = "`namespace`")
public class Namespace {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native",parameters = {@Parameter(name = "sequence_name", value = "namespace_id")})
    private Long id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`uuid`")
    private String uuid;

    @Column(name = "`deleted`")
    private Integer deleted;

    /**
     * Delete time
     */
    @Column(name = "`delete_time`")
    private Long deleteTime;

    @Column(name = "`create_time`")
    private Long createTime;

    @Column(name = "`update_time`")
    private Long updateTime;
}
