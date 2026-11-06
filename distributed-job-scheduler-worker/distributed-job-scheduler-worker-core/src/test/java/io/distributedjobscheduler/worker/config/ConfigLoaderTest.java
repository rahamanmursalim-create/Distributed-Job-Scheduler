package io.distributedjobscheduler.worker.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
public class ConfigLoaderTest {
    @Test
    public void testGetProperties() {
        Properties properties = ConfigLoader.getProperties();
        Assertions.assertNotNull(properties);

        Assertions.assertEquals(properties.getProperty("distributedjobscheduler.worker.test"), "test");
    }
}
