package io.distributedjobscheduler.server.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class HmacUtilTest {
    @Test
    public void testHashPasswd() {
        String key = "3TJPjpUanNqZ0N1";
        String password = "DistributedJobSchedulerPass123";
        String hashValue = HmacUtil.hashPasswd(password, key);
        Assertions.assertTrue(HmacUtil.verifyPasswd(hashValue, password, key));
    }
}
