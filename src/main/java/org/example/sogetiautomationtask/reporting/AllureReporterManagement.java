package org.example.sogetiautomationtask.reporting;

import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public class AllureReporterManagement {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllureReporterManagement.class);

    private static final Properties PROPS;

    private AllureReporterManagement() {}

    static {
        PROPS = new Properties();
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public static byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

    public static void createAllureEnvironmentFile(Map<String, String> propsConfig, String pathToAllureResultsDir) {
        if (Files.exists(Paths.get(pathToAllureResultsDir + config().allureResultsDirectory()))) {
            LOGGER.warn("File already created");
            return;
        }

        propsConfig.forEach(PROPS::setProperty);

        try(FileOutputStream fos = new FileOutputStream(pathToAllureResultsDir + "/environment.properties")) {
            PROPS.store(fos, "Environment properties data for Allure report");
            LOGGER.debug("Allure environment.properties file has been created and stored successfully in the {}", pathToAllureResultsDir);
        } catch (IOException e) {
            LOGGER.error("Something went wrong with environment.properties file creation", e);
        }
    }

    public static void createAllureEnvironmentFile(Map<String, String> propsConfig) {
        createAllureEnvironmentFile(propsConfig, config().allureResultsDirectory());
    }

    public static void storeAllureEnvironmentConfigurations() {
        Map<String, String> envData = new HashMap<>();
        envData.put("Base URL", config().uiBaseUrl());
        envData.put("Browser", config().browserType());
        envData.put("Headless", config().headless().toString());
        envData.put("OS", System.getProperty("os.name"));
        envData.put("OS Version", System.getProperty("os.version"));
        envData.put("Java Version", System.getProperty("java.version"));
        AllureReporterManagement.createAllureEnvironmentFile(envData);
        createAllureEnvironmentFile(envData);
    }
}
