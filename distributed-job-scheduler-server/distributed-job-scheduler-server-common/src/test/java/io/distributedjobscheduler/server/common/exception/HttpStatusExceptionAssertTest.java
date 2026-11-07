package io.distributedjobscheduler.server.common.exception;

import io.distributedjobscheduler.server.common.constant.HttpStatusTestEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class HttpStatusExceptionAssertTest {
    @Test
    public void testThrownException() {
        Assertions.assertThrows(HttpStatusException.class, HttpStatusTestEnum.ONE::throwException);
    }

    @Test
    public void testAssert() {
        Assertions.assertThrows(HttpStatusException.class, () -> HttpStatusTestEnum.ONE.assertIsTrue(false));
    }
}
