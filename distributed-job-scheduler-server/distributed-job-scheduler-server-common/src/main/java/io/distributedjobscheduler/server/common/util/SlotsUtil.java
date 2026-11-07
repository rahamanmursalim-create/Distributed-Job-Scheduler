package io.distributedjobscheduler.server.common.util;

import io.distributedjobscheduler.server.common.ClusterContext;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class SlotsUtil {

    /**
     * Get slots id
     *
     * @param key key
     * @return Long
     */
    public static Long getSlotsId(String key) {
        return (long) (CrcUtil.crc16(key.getBytes()) % ClusterContext.getSystem().getMaxSlot()) + 1;
    }

    /**
     * Get slots id
     *
     * @param key key
     * @return Long
     */
    public static Long getWorkerSupervisorSlotsId(String key) {
        return (long) (CrcUtil.crc16(key.getBytes()) % ClusterContext.getSystem().getWorkerSupervisorSlot()) + 1;
    }
}
