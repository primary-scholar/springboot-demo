package com.mimu.simple.springboot.configurations;

import org.springframework.context.annotation.Configuration;

/**
 author: mimu
 date: 2020/4/22
 */
@Configuration
public class ConfigurationModel {
    private DependenceProperty dependenceProperty;

    ConfigurationModel(DependenceProperty dependenceProperty) {
        this.dependenceProperty = dependenceProperty;
    }

    @Override
    public String toString() {
        return "ConfigurationModel{" +
                "dependenceProperty=" + dependenceProperty +
                '}';
    }
}
