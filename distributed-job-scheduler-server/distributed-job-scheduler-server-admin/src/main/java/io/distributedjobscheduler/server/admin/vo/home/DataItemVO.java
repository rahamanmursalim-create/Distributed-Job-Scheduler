package io.distributedjobscheduler.server.admin.vo.home;

import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.2
 */
@Data
public class DataItemVO {
    private Long total;
    private Long newTotal;

    public DataItemVO(Long total, Long newTotal) {
        this.total = total;
        this.newTotal = newTotal;
    }
}
