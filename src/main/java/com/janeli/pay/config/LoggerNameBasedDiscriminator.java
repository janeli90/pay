package com.janeli.pay.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.sift.AbstractDiscriminator;

/**
 * Created by leo on 2017/11/2.
 */
public class LoggerNameBasedDiscriminator extends AbstractDiscriminator<ILoggingEvent> {

    public static final String KEY = "LOGGERNAME";

    @Override
    public String getDiscriminatingValue(ILoggingEvent iLoggingEvent) {
        String loggerName = iLoggingEvent.getLoggerName();
        int dotIndex = loggerName.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < loggerName.length() - 1) {
            return loggerName.substring(dotIndex + 1, loggerName.length());
        }
        return "unKnow";
    }

    @Override
    public String getKey() {
        return KEY;
    }
}
