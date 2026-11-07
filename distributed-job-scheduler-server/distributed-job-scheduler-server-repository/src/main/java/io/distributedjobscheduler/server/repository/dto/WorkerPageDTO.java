package io.distributedjobscheduler.server.repository.dto;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class WorkerPageDTO {
    /**
     * List page
     */
    private Integer page;

    /**
     * List size. default 10
     */
    private Integer size;

    /**
     * Appid
     */
    private Long appId;

    /**
     * NamespaceId
     */
    private Long namespaceId;

    /**
     * Address
     */
    private String address;
}
