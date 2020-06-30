package com.mimu.simple.spring.szc.inject.anno.support;

import com.mimu.simple.spring.szc.inject.anno.ZKConfigProperty;
import com.mimu.simple.spring.szc.inject.anno.ZKReference;
import com.netflix.config.DynamicStringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 author: mimu
 date: 2020/4/25
 */
public class AnnotationZKPropertyReferenceBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements MergedBeanDefinitionPostProcessor, PriorityOrdered {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationZKPropertyReferenceBeanPostProcessor.class);

    private final Set<Class<? extends Annotation>> referenceAnnotationType = new LinkedHashSet<>(1);
    private String requireInjectParameterName = "key";
    private String requireInjectDefaultValue = "value";

    private final Map<String, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<>(256);

    public AnnotationZKPropertyReferenceBeanPostProcessor() {
        this.referenceAnnotationType.add(ZKReference.class);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        InjectionMetadata metadata = findReferenceMetadata(beanName, beanType, null);
        metadata.checkConfigMembers(beanDefinition);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = findReferenceMetadata(beanName, bean.getClass(), pvs);
        try {
            metadata.inject(bean, beanName, pvs);
        } catch (BeanCreationException ex) {
            throw ex;
        } catch (Throwable throwable) {
            throw new BeanCreationException(beanName, " Injection of zkrefrence failed", throwable);
        }
        return pvs;
    }

    private InjectionMetadata findReferenceMetadata(String beanName, Class<?> clazz, PropertyValues pvs) {
        String cacheKey = StringUtils.hasLength(beanName) ? beanName : clazz.getName();
        InjectionMetadata metadata = this.injectionMetadataCache.get(cacheKey);
        if (InjectionMetadata.needsRefresh(metadata, clazz)) {
            synchronized (this.injectionMetadataCache) {
                metadata = this.injectionMetadataCache.get(cacheKey);
                if (InjectionMetadata.needsRefresh(metadata, clazz)) {
                    if (metadata != null) {
                        metadata.clear(pvs);
                    }
                    metadata = buildReferenceMetadata(clazz);
                    this.injectionMetadataCache.put(cacheKey, metadata);
                }
            }
        }
        return metadata;
    }

    private InjectionMetadata buildReferenceMetadata(final Class<?> clazz) {
        if (!AnnotationUtils.isCandidateClass(clazz, this.referenceAnnotationType)) {
            return InjectionMetadata.EMPTY;
        }
        List<InjectionMetadata.InjectedElement> elements = new ArrayList<>();
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
                    currElements.add(new ZKReferencedFieldElement(field, referencedAnnotation));
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
                    currElements.add(new ZKReferenceMethodElement(method, referencedAnnotation, pd));
                }
            });
            elements.addAll(0, currElements);
            targetClass = targetClass.getSuperclass();
        } while (targetClass != null && targetClass != Object.class);

        return InjectionMetadata.forElements(elements, clazz);
    }

    private boolean determineRequiredStatus(MergedAnnotation<?> ann) {
        return determineRequiredStatus((AnnotationAttributes) ann.asMap(mergedAnnotation ->
                new AnnotationAttributes(mergedAnnotation.getType())
        ));
    }

    private boolean determineRequiredStatus(AnnotationAttributes ann) {
        return StringUtils.hasLength(ann.getString(requireInjectParameterName));
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

    private class ZKReferencedFieldElement extends InjectionMetadata.InjectedElement {
        private final MergedAnnotation<?> annotation;
        private final AnnotationAttributes annotationAttributes;
        private final String key;
        private final String defaultValue;
        private volatile DynamicStringProperty value;

        protected ZKReferencedFieldElement(Member member, MergedAnnotation<?> annotation) {
            super(member, null);
            this.annotation = annotation;
            this.annotationAttributes = this.annotation.asMap(mergedAnnotation ->
                    new AnnotationAttributes(mergedAnnotation.getType()));
            this.key = this.annotationAttributes.getString(requireInjectParameterName);
            this.defaultValue = this.annotationAttributes.getString(requireInjectDefaultValue);
            this.value = ZKConfigProperty.getString(key, defaultValue);
        }

        @Override
        protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
            if (value != null) {
                Field field = (Field) this.member;
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            }
        }
    }

    private class ZKReferenceMethodElement extends InjectionMetadata.InjectedElement {

        private final MergedAnnotation<?> annotation;
        private final AnnotationAttributes annotationAttributes;
        private final String key;
        private final String defaultValue;
        private volatile DynamicStringProperty value;

        protected ZKReferenceMethodElement(Member member, MergedAnnotation<?> annotation, PropertyDescriptor pd) {
            super(member, null);
            this.annotation = annotation;
            this.annotationAttributes = this.annotation.asMap(mergedAnnotation ->
                    new AnnotationAttributes(mergedAnnotation.getType()));
            this.key = this.annotationAttributes.getString(requireInjectParameterName);
            this.defaultValue = this.annotationAttributes.getString(requireInjectDefaultValue);
            this.value = ZKConfigProperty.getString(key, defaultValue);
        }

        @Override
        protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
            if (value != null) {
                Method method = (Method) this.member;
                ReflectionUtils.makeAccessible(method);
                method.invoke(target, value);
            }
        }
    }
}
