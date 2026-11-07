package io.distributedjobscheduler.worker.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class AddressUtilTest {

    @Test
    public void testGetWorkerAddressByLocal() {
        String workerAddressByLocal = AddressUtil.getWorkerAddressByLocal("akka://distributedjobscheduler-worker@172.20.1.33:25531");
        Assertions.assertEquals(workerAddressByLocal, "172.20.1.33:25531");
    }
}
