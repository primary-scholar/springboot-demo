package com.mimu.simple.springboot.dubbo.consumer.config;

import com.mimu.simple.springboot.dubbo.consumer.service.CommonService;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/8/18
 */
@Configuration
@ComponentScan(basePackageClasses = CommonService.class)
@DubboComponentScan(basePackageClasses = CommonService.class)
public class ServiceConsumerConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("sdb-service-consumer");
        return applicationConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(10000000);
        consumerConfig.setRetries(0);
        return consumerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://localhost:2181");
        registryConfig.setClient("curator");
        return registryConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig config = new MonitorConfig();
        config.setProtocol("dubbo");
        return config;
    }

}
