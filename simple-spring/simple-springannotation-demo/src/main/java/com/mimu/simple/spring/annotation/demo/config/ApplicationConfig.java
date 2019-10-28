package com.mimu.simple.spring.annotation.demo.config;

import com.mimu.simple.spring.annotation.demo.CommonController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = CommonController.class)
@Import(value = {AspectConfig.class, DataSourceConfig.class})
public class ApplicationConfig {
}
