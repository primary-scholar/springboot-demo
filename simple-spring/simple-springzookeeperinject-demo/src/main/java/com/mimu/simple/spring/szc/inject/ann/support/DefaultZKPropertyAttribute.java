package com.mimu.simple.spring.szc.inject.ann.support;

import com.mimu.simple.spring.szc.inject.annotation.ZKConfigProperty;
import com.netflix.config.DynamicStringProperty;

/**
 author: mimu
 date: 2020/4/29
 */
public class DefaultZKPropertyAttribute implements ZKPropertyAttribute {
    private DynamicStringProperty property;

    public DefaultZKPropertyAttribute() {
    }

    public DefaultZKPropertyAttribute(String key, String value) {
        property = ZKConfigProperty.getString(key, value);
    }

    public DynamicStringProperty getProperty() {
        return property;
    }

    public void setProperty(DynamicStringProperty property) {
        this.property = property;
    }
}
