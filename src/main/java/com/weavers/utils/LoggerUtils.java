package com.weavers.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class LoggerUtils {
    private static final Logger logger = LogManager.getLogger();

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void startTestCase(String testName) {

        ThreadContext.put("testName", testName);
        logger.info("========================================");
        logger.info("STARTING TEST: {}", testName);
        logger.info("========================================");
    }

    public static void endTestCase(String testName) {
        logger.info("****************************************************************");
        logger.info("END OF TEST: {}", testName);
        logger.info("****************************************************************");

        ThreadContext.remove("testName");
    }
}
