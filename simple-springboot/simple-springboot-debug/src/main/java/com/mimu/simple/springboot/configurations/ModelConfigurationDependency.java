package com.mimu.simple.springboot.configurations;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 author: mimu
 date: 2020/4/22
 */
@Configuration
@EnableConfigurationProperties(ModelProperty.class)
public class ModelConfigurationDependency {

    @Bean
    public ModelDependency dependenceProperty(ModelProperty modelProperty) {
        ModelDependency modelDependency = new ModelDependency();
        modelDependency.setModelProperty(modelProperty);
        return modelDependency;
    }
}
