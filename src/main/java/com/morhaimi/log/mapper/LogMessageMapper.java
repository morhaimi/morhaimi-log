package com.morhaimi.log.mapper;

import com.morhaimi.log.entity.LogMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/27 18:32
 */
@Repository
public interface LogMessageMapper {

    /**
     * 初始化表结构
     */
    void initTable();

    /**
     * 校验表是否存在
     * @param dataBaseName
     * @param tableName
     * @return
     */
    Integer checkTableExists(@Param("dataBaseName") String dataBaseName, @Param("tableName") String tableName);

    /**
     * 写入日志
     * @param logInfos
     */
    void writeLogs(List<LogMessage> logInfos);

}
