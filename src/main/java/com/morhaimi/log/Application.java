package com.morhaimi.log;

import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author xxl
 * @date 2021/9/30 14:36
 */
@Configuration
@MapperScan("com.morhaimi.log.mapper")
@ComponentScan(basePackages = {"cn.hutool.extra.spring"})
@PropertySource({"classpath:sql.properties"})
public class Application {

    @SneakyThrows
    public static Resource[] getMapperLocations() {
        return new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml");
    }

}
