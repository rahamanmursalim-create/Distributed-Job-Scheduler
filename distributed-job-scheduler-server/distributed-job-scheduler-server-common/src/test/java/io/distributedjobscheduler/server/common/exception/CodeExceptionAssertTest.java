package io.distributedjobscheduler.server.common.exception;

import io.distributedjobscheduler.server.common.constant.CodeTestEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class CodeExceptionAssertTest {

    @Test
    public void testThrownException() {
        Assertions.assertThrows(CodeException.class, CodeTestEnum.ONE::throwException);
    }

    @Test
    public void testAssert() {
        Assertions.assertThrows(CodeException.class, () -> CodeTestEnum.ONE.assertIsTrue(false));
    }
}
