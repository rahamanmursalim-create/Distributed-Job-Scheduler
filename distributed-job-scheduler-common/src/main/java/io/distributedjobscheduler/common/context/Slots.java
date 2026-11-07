package io.distributedjobscheduler.common.context;

import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class Slots {
    private List<Long> slotsIds;
}
