package com.mimu.simple.springboot.configurations;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 author: mimu
 date: 2020/4/22
 */
@Configuration
@EnableConfigurationProperties(DependenceProperty.class)
@Import(value = ConfigurationModel.class)
public class ConfigurationProject {

}
