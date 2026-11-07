package io.distributedjobscheduler.worker.config;


import io.distributedjobscheduler.worker.util.ConfigUtil;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Slf4j
public class ConfigLoader {

    /**
     * Env config key.
     */
    private static final String CONFIG_ENV_KEY = "DISTRIBUTED_JOB_SCHEDULER_CONFIG_FILE";

    /**
     * Property config key.
     */
    private static final String CONFIG_PROPERTY_KEY = "distributedjobscheduler.config.file";

    /**
     * Default distributedjobscheduler config file.
     */
    private static final String DEFAULT_CONFIG_FILE = "classpath:distributedjobscheduler.properties";

    /**
     * Properties.
     */
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            load();
        } catch (Throwable e) {
            log.error("Failed to load distributedjobscheduler config file.", e);
        }
    }

    /**
     * Load config file.
     * Order: system properties -> system env -> default file(classpath:distributedjobscheduler.properties) -> legacy path
     */
    private static void load() {
        // File name.
        String filename = System.getProperty(CONFIG_PROPERTY_KEY);
        if (StringUtils.isBlank(filename)) {
            filename = System.getenv(CONFIG_ENV_KEY);
            if (StringUtils.isBlank(filename)) {
                filename = DEFAULT_CONFIG_FILE;
            }
        }

        // Load properties.
        Properties prop = ConfigUtil.loadProperties(filename);

        // Load info.
        if (Objects.nonNull(prop) && !prop.isEmpty()) {
            PROPERTIES.putAll(prop);
            log.info("Loading distributedjobscheduler config from {}", filename);
        }

        // Use JVM parameter to override.
        for (Map.Entry<Object, Object> entry : new CopyOnWriteArraySet<>(System.getProperties().entrySet())) {
            String configKey = entry.getKey().toString();
            String newConfigValue = entry.getValue().toString();
            String oldConfigValue = PROPERTIES.getProperty(configKey);
            PROPERTIES.put(configKey, newConfigValue);
            if (oldConfigValue != null) {
                log.info("JVM parameter overrides {}: {} -> {}", configKey, oldConfigValue, newConfigValue);
            }
        }
    }

    /**
     * Get properties.
     *
     * @return Properties
     */
    public static Properties getProperties() {
        return PROPERTIES;
    }
}
