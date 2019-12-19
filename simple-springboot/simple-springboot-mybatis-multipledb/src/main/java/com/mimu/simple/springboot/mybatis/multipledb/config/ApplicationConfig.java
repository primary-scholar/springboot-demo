package com.mimu.simple.springboot.mybatis.multipledb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * author: mimu
 * date: 2019/10/30
 */
@Configuration
@Import(value = {DataSourceConfig.class, MybatisConfig.class, AspectConfig.class})
public class ApplicationConfig {
}
