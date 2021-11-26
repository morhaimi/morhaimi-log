package com.morhaimi.log.service.impl;

import com.morhaimi.log.mapper.LogMessageMapper;
import com.morhaimi.log.entity.LogMessage;
import com.morhaimi.log.service.LogMessageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

/**
 *
 * @author xxl
 * @date 2021/9/24 17:03
 */
@Slf4j
@Service
public class LogMessageServiceImpl implements LogMessageService {

    @Autowired
    LogMessageMapper logMessageMapper;

    @Autowired
    DataSource dataSource;

    @Override
    @SneakyThrows
    public void initTable() {
        Connection connection = dataSource.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        String url = metaData.getURL();
        String databaseName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("?"));

        Integer count = logMessageMapper.checkTableExists(databaseName, "sys_log_message");

        if (count != null && count > 0) {
            return;
        }
        logMessageMapper.initTable();
    }

    @Override
    public void writeLogs(List<LogMessage> logInfos) {
        logMessageMapper.writeLogs(logInfos);
    }

}
