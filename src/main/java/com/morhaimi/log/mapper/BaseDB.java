package com.morhaimi.log.mapper;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

/**
 * @author xxl
 * @date 2022/9/23
 */
public class BaseDB {

    @Autowired
    DataSource dataSource;

    @SneakyThrows
    public Connection getConnection() {
        return dataSource.getConnection();
    }

    @SneakyThrows
    public String getDatabaseName() {
        DatabaseMetaData metaData = getConnection().getMetaData();
        String url = metaData.getURL();
        return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("?"));
    }

}
