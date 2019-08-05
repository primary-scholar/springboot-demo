package com.mimu.simple.sj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.mimu.simple.sj")
@Import(value = {DataSourceConfig.class})
@EnableWebMvc
public class ApplicationConfig {
}
