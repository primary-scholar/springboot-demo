package com.mimu.simple.spring.szc.inject.ann.support;

import com.alibaba.fastjson.JSONObject;
import com.netflix.config.DynamicStringProperty;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
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
            return getValue(returnType, property == null ? null : property.get());
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
        if (returnType == Integer.class) {
            return NumberUtils.toInt(value, 0);
        }
        if (returnType == Long.class) {
            return NumberUtils.toLong(value, 0l);
        }
        if (returnType == Float.class) {
            return NumberUtils.toFloat(value, 0.0f);
        }
        if (returnType == Double.class) {
            return NumberUtils.toDouble(value, 0.0d);
        }
        if (returnType == Short.class) {
            return NumberUtils.toShort(value, (short) 0);
        }
        if (returnType == Boolean.class) {
            return NumberUtils.isDigits(value) ? BooleanUtils.toBoolean(NumberUtils.toInt(value)) : BooleanUtils.toBoolean(value);
        }
        try {
            return JSONObject.toJavaObject(JSONObject.parseObject(value), returnType);
        } catch (Exception e) {
            return null;
        }
    }
}
