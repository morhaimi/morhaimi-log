package com.morhaimi.log.mapper;

import com.morhaimi.log.entity.LogMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author xxl
 * @date 2022/9/23
 */
@Repository
public class LogMessageMapper extends BaseDB {

    @Value("${checkTableExists}")
    private String checkTableSql;

    @Value("${createTable}")
    private String createTable;

    @Value("${insertLog}")
    private String insertLog;

    /**
     * 检查表结构是否存在
     * @param tableName
     * @return
     */
    @SneakyThrows
    public Integer checkTableExists(String tableName) {
        PreparedStatement preparedStatement = getConnection().prepareStatement(checkTableSql);
        preparedStatement.setString(1, getDatabaseName());
        preparedStatement.setString(2, tableName);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    /**
     * 创建表结构
     */
    @SneakyThrows
    public void initTable() {
        PreparedStatement preparedStatement = getConnection().prepareStatement(createTable);
        preparedStatement.execute();
    }

    @SneakyThrows
    public void writeLogs(List<LogMessage> logInfos) {
        if (logInfos.size() <= 0) {
            return;
        }
        for (LogMessage logMessage : logInfos) {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insertLog);
            preparedStatement.setString(1, logMessage.getThreadName());
            preparedStatement.setString(2, logMessage.getIp());
            preparedStatement.setString(3, logMessage.getLoggerName());
            preparedStatement.setInt(4, logMessage.getLine());
            preparedStatement.setString(5, logMessage.getLevel());
            preparedStatement.setString(6, logMessage.getMessage());
            preparedStatement.setLong(7, logMessage.getTimeStamp());
            preparedStatement.setObject(8, logMessage.getDateInfo());
            preparedStatement.executeUpdate();
        }
    }

}
