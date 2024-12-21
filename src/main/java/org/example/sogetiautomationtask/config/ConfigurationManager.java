package org.example.sogetiautomationtask.config;

import org.aeonbits.owner.ConfigCache;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConfigurationManager {
    private static final Logger logger = Logger.getLogger(ConfigurationManager.class.getName());

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
            logger.log(Level.SEVERE, "Failed to load configuration file", e);
        }

        configFileProperties.putAll(systemProperties);

        return ConfigCache.getOrCreate(Configuration.class, configFileProperties);
    }
}
