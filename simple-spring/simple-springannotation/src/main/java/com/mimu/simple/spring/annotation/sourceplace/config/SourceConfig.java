package com.mimu.simple.spring.annotation.sourceplace.config;


import com.mimu.simple.spring.annotation.sourceplace.model.DomainA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * author: mimu
 * date: 2018/12/4
 */
@Configuration
@PropertySource(value = "classpath:source.properties")
@ComponentScan(basePackageClasses = DomainA.class)
public class SourceConfig {

    /**
     * 不添加 PropertySourcesPlaceholderConfigurer 该实例 DomainA 中同样可以获取参数值
     */
    /*@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/
}
