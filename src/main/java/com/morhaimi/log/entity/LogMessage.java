package com.morhaimi.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xxl
 * @date 2021/9/27 18:36
 */
@Data
@TableName("sys_log_message")
public class LogMessage {
    /**
     * 时间戳
     */
    private Long timeStamp;
    /**
     * 线程名字
     */
    private String threadName;
    /**
     * 本地ip
     */
    private String ip;
    /**
     * 日志写入名称
     */
    private String loggerName;
    /**
     * 日志打印行
     */
    private Integer line;
    /**
     * 日志等级
     */
    private String level;
    /**
     * 日志消息
     */
    private String message;
    /**
     * 阅读状态：1.未读，2.已读
     */
    private Integer readState;
    /**
     * 时间信息
     */
    private LocalDateTime dateInfo;
}
