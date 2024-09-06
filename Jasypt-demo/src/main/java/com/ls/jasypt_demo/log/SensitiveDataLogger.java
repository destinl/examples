package com.ls.jasypt_demo.log;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/6 23:10
 */
import com.ls.jasypt_demo.domain.Enum.SensitiveStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SensitiveDataLogger {
    private static final Logger logger = LoggerFactory.getLogger(SensitiveDataLogger.class);

    public static void info(String message) {
        logger.info(maskSensitiveData(message));
    }

    private static String maskSensitiveData(String message) {
        for (SensitiveStrategy strategy : SensitiveStrategy.values()) {
            message = strategy.desensitizer().apply(message);
        }
        return message;
    }
}