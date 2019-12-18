package com.mimu.simple.springboot.mybatis.multipledb;

import com.mimu.simple.springboot.mybatis.multipledb.config.ApplicationConfig;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 author: mimu
 date: 2019/12/18
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class, MybatisAutoConfiguration.class})
@Import(value = {ApplicationConfig.class})
public class SBMMDApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SBMMDApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run();
    }
}
