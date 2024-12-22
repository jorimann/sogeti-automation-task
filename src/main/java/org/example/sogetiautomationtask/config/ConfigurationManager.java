package org.example.sogetiautomationtask.config;

import org.aeonbits.owner.ConfigCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationManager.class);


    private ConfigurationManager (){};

    public static Configuration config() {
        Properties systemProperties = new Properties();
        Properties configFileProperties = new Properties();
        String fileName = "config-default.properties";

        systemProperties.putAll(System.getProperties());

        if (System.getProperty("env") != null) {
            fileName = "config-" + System.getProperty("env") + ".properties";
        }

        try{
            configFileProperties.load(ClassLoader.getSystemClassLoader()
                        .getResourceAsStream(fileName));
        } catch (IOException e) {
            LOGGER.error("Failed to load configuration file", e);
        }

        configFileProperties.putAll(systemProperties);

        return ConfigCache.getOrCreate(Configuration.class, configFileProperties);
    }
}
