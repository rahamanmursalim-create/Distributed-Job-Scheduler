package io.distributedjobscheduler.worker.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
public class ConfigUtilTest {
    @Test
    public void testClassPathFile() {
        Properties properties = ConfigUtil.loadProperties("classpath:distributedjobscheduler.properties");
        Assertions.assertNotNull(properties);

        String value = properties.getProperty("distributedjobscheduler.worker.test", "none");
        Assertions.assertEquals(value, "test");
    }
}
