package com.mimu.simple.springboot.zkr.zkconfig;

import com.mimu.simple.springboot.zkr.annotation.interceptor.ZKPropertyAspectSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(ZKPropertyAspectSupport.class)
@EnableConfigurationProperties(ZKConfigProperties.class)
public class ZKConfigCenterAutoConfiguration {

    @Bean
    public ZKConfigResource zkConfigResource(ZKConfigProperties zkConfigProperties) {
        ZKConfigResource zkConfigResource = new ZKConfigResource(zkConfigProperties.getAddress(), zkConfigProperties.getPath());
        return zkConfigResource;
    }
}
