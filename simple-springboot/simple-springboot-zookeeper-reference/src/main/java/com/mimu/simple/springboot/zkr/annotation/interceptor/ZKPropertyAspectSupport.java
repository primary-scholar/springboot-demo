package com.mimu.simple.springboot.zkr.annotation.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.mimu.simple.springboot.zkr.zkconfig.ZKConfigResource;
import com.netflix.config.DynamicStringProperty;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.cache.CacheManager;

import java.lang.reflect.Method;

/**
 * author: mimu
 * date: 2020/4/28
 */
public class ZKPropertyAspectSupport implements BeanFactoryAware, InitializingBean, SmartInitializingSingleton {

    private BeanFactory beanFactory;
    private ZKPropertyAttributeSource propertyAttribute;
    private ZKConfigResource zkConfigResource;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ZKConfigResource getZkConfigResource() {
        return zkConfigResource;
    }

    public void setZkConfigResource(ZKConfigResource zkConfigResource) {
        this.zkConfigResource = zkConfigResource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.beanFactory == null) {
            throw new RuntimeException("running in a listableBeanFactory is required!");
        }
        if (this.propertyAttribute == null) {
            throw new RuntimeException("propertyAttributeSource is required!");
        }

    }

    @Override
    public void afterSingletonsInstantiated() {
        setZkConfigResource(this.beanFactory.getBean(ZKConfigResource.class));
    }

    public Object invokeWithInterceptor(Method method, Class<?> targetClass) {
        ZKPropertyAttributeSource attributeSource = getPropertyAttribute();
        ZKPropertyAttribute attribute = attributeSource != null ? attributeSource.getProperAttribute(method, targetClass) : null;
        if (attribute != null) {
            DynamicStringProperty property = getZkConfigResource().getString(attribute.getReference(), attribute.getValue());
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
        String name = returnType.getName();
        switch (name) {
            case "int":
                return NumberUtils.toInt(value, 0);
            case "long":
                return NumberUtils.toLong(value, 0l);
            case "float":
                return NumberUtils.toFloat(value, 0.0f);
            case "double":
                return NumberUtils.toDouble(value, 0.0d);
            case "short":
                return NumberUtils.toShort(value, (short) 0);
            case "boolean":
                return BooleanUtils.toBoolean(value);
            case "java.lang.String":
                return value;
        }
        try {
            return JSONObject.toJavaObject(JSONObject.parseObject(value), returnType);
        } catch (Exception e) {
            return null;
        }
    }

}
