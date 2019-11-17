package com.mimu.simple.spring.annotation.demo.config;

import com.mimu.simple.spring.annotation.demo.CommonController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * author: mimu
 * date: 2018/12/4
 */
@Configuration
@ComponentScan(basePackageClasses = CommonController.class)
@Import(value = {AspectConfig.class, DataSourceConfig.class})
public class ApplicationConfig {
}
