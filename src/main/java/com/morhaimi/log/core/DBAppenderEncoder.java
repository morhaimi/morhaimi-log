package com.morhaimi.log.core;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.encoder.EncoderBase;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson.JSON;
import com.morhaimi.log.entity.LogMessage;
import com.morhaimi.log.utils.IPUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author xxl
 * @date 2021/9/24 10:04
 */
public class DBAppenderEncoder extends EncoderBase<LoggingEvent> {
    @Override
    public byte[] headerBytes() {
        return new byte[0];
    }

    @Override
    public byte[] encode(LoggingEvent event) {
        LogMessage logInfo = new LogMessage();
        logInfo.setTimeStamp(event.getTimeStamp());
        logInfo.setThreadName(event.getThreadName());
        logInfo.setLoggerName(event.getLoggerName());
        logInfo.setLevel(event.getLevel().toString());
        logInfo.setIp(IPUtils.getLocalIp());
        logInfo.setReadState(1);
        logInfo.setLine(event.getCallerData()[0].getLineNumber());
        logInfo.setDateInfo(LocalDateTimeUtil.of(event.getTimeStamp()));
        if(event.getThrowableProxy() != null){
            ThrowableProxy throwableProxy = (ThrowableProxy) event.getThrowableProxy();
            logInfo.setMessage(event.getFormattedMessage() + "\n" +
                    ExceptionUtils.getStackTrace(throwableProxy.getThrowable()));
        } else {
            logInfo.setMessage(event.getFormattedMessage());
        }
        return JSON.toJSONString(logInfo).getBytes();
    }

    @Override
    public byte[] footerBytes() {
        return new byte[0];
    }
}
