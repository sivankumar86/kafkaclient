package com.kafka.learn.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;


public class ApplicationProperties {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);

    public  Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("client.properties"));
        } catch (IOException e) {
            logger.error("Not able to load property");
        }
        return properties;
    }

    /**
     *
     * @param filePath
     * @return
     */
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(filePath));
        } catch (IOException e) {
            logger.error("Not able to load property");
        }
        return properties;
    }
}
