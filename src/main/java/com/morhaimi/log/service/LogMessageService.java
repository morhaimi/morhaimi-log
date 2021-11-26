package com.morhaimi.log.service;

import com.morhaimi.log.entity.LogMessage;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/24 17:02
 */
public interface LogMessageService {

    /**
     * 初始化表结构
     */
    void initTable();

    /**
     * 写入日志
     * @param logInfos
     */
    void writeLogs(List<LogMessage> logInfos);

}
