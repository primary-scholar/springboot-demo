package com.mimu.simple.spring.szc.inject.annotation;

import com.mimu.simple.spring.szc.inject.ZKConfigCenter;
import com.netflix.config.DynamicStringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 author: mimu
 date: 2020/4/25
 */
public class AnnotationZKCenterReferenceBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements MergedBeanDefinitionPostProcessor, PriorityOrdered {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationZKCenterReferenceBeanPostProcessor.class);

    private final Set<Class<? extends Annotation>> referenceAnnotationType = new LinkedHashSet<>(1);
    private String requeireParameterName = "key";

    private final Map<String, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<>(256);

    public AnnotationZKCenterReferenceBeanPostProcessor() {
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
                MergedAnnotation<?> ann = findReferencedAnnotation(field);
                if (ann != null) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        if (logger.isInfoEnabled()) {
                            logger.info("ZKReference annotation is not supported on static fileds:" + field);
                        }
                        return;
                    }
                    boolean required = determineRequiredStatus(ann);
                    currElements.add(new ZKReferencedElement(field, ann, required));
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
        return StringUtils.hasLength(ann.getString(requeireParameterName));
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

    private class ZKReferencedElement extends InjectionMetadata.InjectedElement {
        private final MergedAnnotation<?> annotation;
        private final AnnotationAttributes annotationAttributes;
        private final String key;
        private final String defaultValue;
        private volatile DynamicStringProperty value;

        protected ZKReferencedElement(Member member, MergedAnnotation<?> annotation, boolean required) {
            super(member, null);
            this.annotation = annotation;
            this.annotationAttributes = this.annotation.asMap(mergedAnnotation ->
                    new AnnotationAttributes(mergedAnnotation.getType()));
            this.key = this.annotationAttributes.getString("key");
            this.defaultValue = this.annotationAttributes.getString("value");
            this.value = ZKConfigCenter.getString(key, defaultValue);
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
}
