package io.distributedjobscheduler.server.admin.util;

import org.junit.jupiter.api.Test;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class LogFormatTest {
    @Test
    public void testFormatLocation() {
        String location = "io.distributedjobscheduler.worker.samples.processor.JavaProcessorSample.process(JavaProcessorSample.java:25)";
        System.out.println(LogFormatUtil.formatLocation(location));
    }
}
