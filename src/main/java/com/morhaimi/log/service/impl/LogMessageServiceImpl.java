package com.morhaimi.log.service.impl;

import com.morhaimi.log.entity.LogMessage;
import com.morhaimi.log.mapper.LogMessageMapper;
import com.morhaimi.log.service.LogMessageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author xxl
 * @date 2021/9/24 17:03
 */
@Slf4j
@Service
public class LogMessageServiceImpl implements LogMessageService {

    @Resource
    LogMessageMapper logMessageMapper;

    @Override
    @SneakyThrows
    public void initTable() {
        Integer count = logMessageMapper.checkTableExists("sys_log_message");
        if (count > 0) {
            return;
        }
        logMessageMapper.initTable();
    }

    @Override
    public void writeLogs(List<LogMessage> logInfos) {
        logMessageMapper.writeLogs(logInfos);
    }

}
