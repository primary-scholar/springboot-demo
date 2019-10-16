package com.mimu.simple.sd.consumer.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/8/18
 */
@Configuration
@ComponentScan(basePackages = {"com.mimu.simple.sd.consumer"})
@EnableDubbo(scanBasePackages = {"com.mimu.simple.sd.consumer"})
public class ServiceConsumerConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("springd-service-consumer");
        applicationConfig.setQosEnable(false);
        return applicationConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(300000);
        return consumerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://localhost:2181");
        registryConfig.setClient("curator");
        return registryConfig;
    }

    /*@Bean
    public ReferenceConfig<HelloStringApi> helloStringApiReferenceConfig(){
        return new ReferenceConfig<>();
    }*/

}
