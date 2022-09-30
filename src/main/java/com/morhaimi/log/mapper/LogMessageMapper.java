package com.morhaimi.log.mapper;

import com.morhaimi.log.entity.LogMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Update("CREATE TABLE `sys_log_message` (\n" +
            "           `id` int NOT NULL AUTO_INCREMENT,\n" +
            "           `thread_name` varchar(500) DEFAULT NULL COMMENT '线程名',\n" +
            "           `ip` varchar(20) DEFAULT NULL COMMENT '本地ip',\n" +
            "           `logger_name` varchar(500) DEFAULT NULL COMMENT '日志写入名称',\n" +
            "           `line` int DEFAULT NULL COMMENT '日志记录行',\n" +
            "           `level` varchar(10) DEFAULT NULL COMMENT '日志级别',\n" +
            "           `message` text COMMENT '日志内容',\n" +
            "           `read_state` int DEFAULT NULL COMMENT '阅读状态：1.未读，2.已读',\n" +
            "           `time_stamp` bigint DEFAULT NULL COMMENT '时间戳',\n" +
            "           `date_info` datetime DEFAULT NULL COMMENT '时间信息',\n" +
            "           PRIMARY KEY (`id`)\n" +
            "        ) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='日志记录表';")
    void initTable();

    /**
     * 校验表是否存在
     * @param dataBaseName
     * @param tableName
     * @return
     */
    @Select("SELECT\n" +
            "            COUNT(1)\n" +
            "        FROM INFORMATION_SCHEMA.TABLES\n" +
            "        WHERE TABLE_SCHEMA = #{dataBaseName} AND TABLE_NAME = #{tableName}")
    Integer checkTableExists(@Param("dataBaseName") String dataBaseName, @Param("tableName") String tableName);

    /**
     * 写入日志
     * @param logInfos
     */
    @Insert("<script>\n" +
            "   insert into sys_log_message (thread_name, ip, logger_name, line, level, message, read_state, time_stamp, date_info) values\n" +
            "   <foreach collection=\"list\" separator=\",\" item=\"item\">\n" +
            "       (#{item.threadName}, #{item.ip}, #{item.loggerName}, #{item.line}, #{item.level},\n" +
            "       #{item.message}, #{item.readState}, #{item.timeStamp}, #{item.dateInfo})\n" +
            "   </foreach>\n" +
            "</script>")
    void writeLogs(List<LogMessage> logInfos);

}
