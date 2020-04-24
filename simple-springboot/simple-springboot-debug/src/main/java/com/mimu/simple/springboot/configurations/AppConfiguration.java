package com.mimu.simple.springboot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 author: mimu
 date: 2020/4/22
 */
@Configuration
@Import(value = ModelConfigurationDependency.class)
public class AppConfiguration {

}
