package com.morhaimi.log;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xxl
 * @date 2021/9/30 14:36
 */
@Configuration
@MapperScan("com.morhaimi.log.mapper")
@ComponentScan(basePackages = {"cn.hutool.extra.spring"})
public class Application {

}
