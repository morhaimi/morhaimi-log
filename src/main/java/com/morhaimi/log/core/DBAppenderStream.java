package com.morhaimi.log.core;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.morhaimi.log.entity.LogMessage;
import com.morhaimi.log.service.LogMessageService;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xxl
 * @date 2021/9/23 19:52
 */
public class DBAppenderStream extends OutputStream {

    List<LogMessage> writeCache = Lists.newArrayList();
    ReentrantLock lock = new ReentrantLock();

    public DBAppenderStream() {
        timerFlush();
    }

    @SneakyThrows
    private void timerFlush(){
        ThreadUtil.execAsync(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                ThreadUtil.sleep(20000);
                flush();
                timerFlush();
            }

        }, true);
    }

    @Override
    public void write(int b) throws IOException {
        throw new IOException("该方法不支持");
    }

    @Override
    public void write(byte[] b) throws IOException {
        lock.lock();
        try{
            LogMessage logInfo = JSON.parseObject(new String(b), LogMessage.class);
            if (!"main".equals(logInfo.getThreadName())) {
                writeCache.add(logInfo);
            }

            flush();

        } finally {
            lock.unlock();
        }
    }

    @Override
    public void flush() throws IOException {
        if(writeCache.size() == 0){
            return;
        }

        List<LogMessage> doFlushCache = Lists.newArrayList();

        lock.lock();
        try {
            doFlushCache.addAll(writeCache);
            writeCache.clear();
        } finally {
            lock.unlock();
        }

        SpringUtil.getBean(LogMessageService.class).writeLogs(doFlushCache);
    }

}
