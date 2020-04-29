package com.mimu.simple.spring.szc.inject.ann.support;

import com.netflix.config.DynamicStringProperty;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Method;

/**
 author: mimu
 date: 2020/4/28
 */
public class ZKPropertyAspectSupport implements BeanFactoryAware, InitializingBean {

    private BeanFactory beanFactory;
    private ZKPropertyAttributeSource propertyAttribute;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    protected Object invokeWithIntercept(Method method, Class<?> targetClass) {
        ZKPropertyAttributeSource attributeSource = getPropertyAttribute();
        ZKPropertyAttribute attribute = attributeSource != null ? attributeSource.getProperAttribute(method, targetClass) : null;
        if (attribute != null) {
            DynamicStringProperty property = ((DefaultZKPropertyAttribute) attribute).getProperty();
            Class<?> returnType = method.getReturnType();
            return getValue(returnType, property.get());
        }
        return null;
    }


    public ZKPropertyAttributeSource getPropertyAttribute() {
        return propertyAttribute;
    }

    public void setPropertyAttribute(ZKPropertyAttributeSource propertyAttribute) {
        this.propertyAttribute = propertyAttribute;
    }

    public Object getValue(Class<?> returnType, String value) {
        String name = returnType.getName();
        switch (name) {
            case "int":
                return Integer.parseInt(value);
            case "long":
                return Long.parseLong(value);
            case "float":
                return Float.parseFloat(value);
            case "double":
                return Double.parseDouble(value);
            case "short":
                return Short.parseShort(value);
            case "boolean":
                return Boolean.parseBoolean(value);
        }
        return value;
    }
}
