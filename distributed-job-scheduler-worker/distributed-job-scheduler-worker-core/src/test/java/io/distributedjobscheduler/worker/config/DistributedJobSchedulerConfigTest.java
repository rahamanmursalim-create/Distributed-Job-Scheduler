package io.distributedjobscheduler.worker.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class DistributedJobSchedulerConfigTest {
    @Test
    public void testGetConfig() {
        Assertions.assertEquals(DistributedJobSchedulerConfig.getConfig("distributedjobscheduler.worker.test"), "test");
        Assertions.assertEquals(DistributedJobSchedulerConfig.getString("distributedjobscheduler.worker.test"), "test");

        Assertions.assertEquals(DistributedJobSchedulerConfig.getInteger("distributedjobscheduler.worker.int.value"), 1);
        Assertions.assertEquals(DistributedJobSchedulerConfig.getLong("distributedjobscheduler.worker.long.value"), 1L);
    }
}
