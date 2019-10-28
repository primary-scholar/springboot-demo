package com.mimu.simple.spring.mybatis.config;

import com.mimu.simple.spring.mybatis.CommonService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * author: mimu
 * date: 2019/10/28
 */
@Configuration
@Import(value = DataSourceConfig.class)
@ComponentScan(basePackageClasses = CommonService.class)
public class ApplicationConfig {
}
