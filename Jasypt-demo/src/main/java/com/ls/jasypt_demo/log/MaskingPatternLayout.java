package com.ls.jasypt_demo.log;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.ls.jasypt_demo.domain.Enum.SensitiveStrategy;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/6 23:03
 */
public class MaskingPatternLayout extends PatternLayout {
    @Override
    public String doLayout(ILoggingEvent event) {
        String message = super.doLayout(event);
        message = maskSensitiveData(message);
        return message;
    }

    private String maskSensitiveData(String message) {
        for (SensitiveStrategy strategy : SensitiveStrategy.values()) {
            message = strategy.desensitizer().apply(message);
        }
        return message;
    }
}
