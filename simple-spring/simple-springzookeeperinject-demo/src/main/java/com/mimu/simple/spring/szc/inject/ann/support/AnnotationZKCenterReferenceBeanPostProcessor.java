package com.mimu.simple.spring.szc.inject.ann.support;

import com.mimu.simple.spring.szc.inject.annotation.ZKReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 author: mimu
 date: 2020/4/25
 */
public class AnnotationZKCenterReferenceBeanPostProcessor implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationZKCenterReferenceBeanPostProcessor.class);

    private final Set<Class<? extends Annotation>> referenceAnnotationType = new LinkedHashSet<>(1);
    private String requireInjectParameterName = "key";
    private String requireInjectDefaultValue = "value";

    public AnnotationZKCenterReferenceBeanPostProcessor() {
        this.referenceAnnotationType.add(ZKReference.class);
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        if (AnnotationUtils.isCandidateClass(clazz, this.referenceAnnotationType)) {
            Class<?> targetClass = clazz;
            do {
                final List<InjectionMetadata.InjectedElement> currElements = new ArrayList<>();
                ReflectionUtils.doWithLocalFields(targetClass, field -> {
                    MergedAnnotation<?> referencedAnnotation = findReferencedAnnotation(field);
                    if (referencedAnnotation != null) {
                        if (Modifier.isStatic(field.getModifiers())) {
                            if (logger.isInfoEnabled()) {
                                logger.info("ZKReference annotation is not supported on static fields:" + field);
                            }
                            return;
                        }
                        boolean required = determineRequiredStatus(referencedAnnotation);
                    }
                });
                ReflectionUtils.doWithLocalMethods(targetClass, method -> {
                    Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
                    if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod)) {
                        return;
                    }
                    MergedAnnotation<?> referencedAnnotation = findReferencedAnnotation(bridgedMethod);
                    if (referencedAnnotation != null && method.equals(ClassUtils.getMostSpecificMethod(method, clazz))) {
                        if (Modifier.isStatic(method.getModifiers())) {
                            if (logger.isInfoEnabled()) {
                                logger.info("ZKReference annotation is not supported on static methods:" + method);
                            }
                            return;
                        }
                        if (method.getParameterCount() == 0) {
                            if (logger.isInfoEnabled()) {
                                logger.info("ZKReference annotation should only be used on methods with parameters:" + method);
                            }
                        }
                        boolean requiredStatus = determineRequiredStatus(referencedAnnotation);
                        PropertyDescriptor pd = BeanUtils.findPropertyForMethod(bridgedMethod, clazz);
                    }
                });
                targetClass = targetClass.getSuperclass();
            } while (targetClass != null && targetClass != Object.class);
        }
        return bean;
    }


    private MergedAnnotation<?> findReferencedAnnotation(AccessibleObject ao) {
        MergedAnnotations annotations = MergedAnnotations.from(ao);
        for (Class<? extends Annotation> type : this.referenceAnnotationType) {
            MergedAnnotation<?> annotation = annotations.get(type);
            if (annotation.isPresent()) {
                return annotation;
            }
        }
        return null;
    }

    private boolean determineRequiredStatus(MergedAnnotation<?> ann) {
        return determineRequiredStatus((AnnotationAttributes) ann.asMap(mergedAnnotation ->
                new AnnotationAttributes(mergedAnnotation.getType())
        ));
    }

    private boolean determineRequiredStatus(AnnotationAttributes ann) {
        return StringUtils.hasLength(ann.getString(requireInjectParameterName));
    }
}
