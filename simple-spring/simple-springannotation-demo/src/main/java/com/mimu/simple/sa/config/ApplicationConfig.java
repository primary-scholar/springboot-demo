package com.mimu.simple.sa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.mimu.simple.sa")
@Import(value = {AspectConfig.class, DataSourceConfig.class})
public class ApplicationConfig {
}
