package com.mimu.simple.spring.szc.inject.ann.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 author: mimu
 date: 2020/4/28
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyZKPropertyConfiguration extends AbstractPropertyConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public BeanFactoryZKPropertyAttributeSourceAdvisor zkPropertyAttributeSourceAdvisor() {
        BeanFactoryZKPropertyAttributeSourceAdvisor advisor = new BeanFactoryZKPropertyAttributeSourceAdvisor();
        advisor.setZkPropertyAttributeSource(propertyAttributeSource());
        advisor.setAdvice(propertyInterceptor());
        return advisor;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public ZKPropertyAttributeSource propertyAttributeSource() {
        return new AnnotationZkPropertyAttributeSource();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public ZKPropertyInterceptor propertyInterceptor() {
        ZKPropertyInterceptor interceptor = new ZKPropertyInterceptor();
        interceptor.setPropertyAttribute(propertyAttributeSource());
        return interceptor;
    }

}
