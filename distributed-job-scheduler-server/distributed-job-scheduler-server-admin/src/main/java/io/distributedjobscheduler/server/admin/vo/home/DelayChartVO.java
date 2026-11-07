package io.distributedjobscheduler.server.admin.vo.home;

import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.2
 */
@Data
public class DelayChartVO {
    private List<String> axisData;
    private List<Long> successData;
    private List<Long> failData;
    private List<Long> runningData;
    private List<Long> percentList;
}
