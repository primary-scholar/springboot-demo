package com.mimu.simple.spring.jetty.config;

import com.mimu.simple.spring.jetty.AppSimpleJettyServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackageClasses = AppSimpleJettyServer.class)
@Import(value = {DataSourceConfig.class})
@EnableWebMvc
public class ApplicationConfig {
}
