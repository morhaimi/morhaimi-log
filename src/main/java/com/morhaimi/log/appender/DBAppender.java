package com.morhaimi.log.appender;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.OutputStreamAppender;
import com.morhaimi.log.core.DBAppenderEncoder;
import com.morhaimi.log.core.DBAppenderStream;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author xxl
 * @date 2021/9/23 19:15
 */
public class DBAppender extends OutputStreamAppender<LoggingEvent> {

    public DBAppender() {

    }

    @Override
    public void start() {
        setOutputStream(new DBAppenderStream());
        setEncoder(new DBAppenderEncoder());
        super.start();
    }

    @Override
    @SneakyThrows
    public void stop() {
        getOutputStream().flush();
        super.stop();
    }

}
