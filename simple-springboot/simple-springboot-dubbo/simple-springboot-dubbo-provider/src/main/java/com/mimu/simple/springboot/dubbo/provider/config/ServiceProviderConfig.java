package com.mimu.simple.springboot.dubbo.provider.config;

import com.mimu.simple.springboot.dubbo.provider.service.UserDataApiImpl;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.context.annotation.Configurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig config = new MonitorConfig();
        config.setProtocol("dubbo");
        return config;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        Map paramater = new HashMap<String,String>();
        paramater.put("scope", "remote");
        protocolConfig.setParameters(paramater);
        protocolConfig.setName("dubbo");
        protocolConfig.setDispatcher("message");
        protocolConfig.setThreads(200);
        protocolConfig.setPort(20880);
        return protocolConfig;
    }
}
