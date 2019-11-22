package com.mimu.simple.springboot.dubbo.provider.config;

import com.mimu.simple.springboot.dubbo.provider.service.UserDataApiImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/8/18
 */
@Configuration
@DubboComponentScan(basePackageClasses = UserDataApiImpl.class)
public class ServiceProviderConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("sbd-service-provider");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://localhost:2181");
        registryConfig.setClient("curator");
        return registryConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig config = new ProviderConfig();
        config.setTimeout(3000);
        return config;
    }
}
