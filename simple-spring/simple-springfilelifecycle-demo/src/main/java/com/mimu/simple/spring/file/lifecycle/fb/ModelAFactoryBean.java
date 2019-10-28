package com.mimu.simple.spring.file.lifecycle.fb;

import org.springframework.beans.factory.FactoryBean;

/**
 * author: mimu
 * date: 2019/9/25
 */
public class ModelAFactoryBean implements FactoryBean<ModelA> {
    @Override
    public ModelA getObject() throws Exception {
        return new ModelA();
    }

    @Override
    public Class<?> getObjectType() {
        return ModelA.class;
    }
}
