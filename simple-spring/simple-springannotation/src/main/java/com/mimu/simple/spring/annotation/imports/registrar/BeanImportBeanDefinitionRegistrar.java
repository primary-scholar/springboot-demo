package com.mimu.simple.spring.annotation.imports.registrar;

import com.mimu.simple.spring.annotation.imports.api.Hello;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class BeanImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Set<String> packageToScan = getPackageToScan(importingClassMetadata);
        ClassPathBeanDefinitionScanner scanner = getScanner(registry);
        scanner.scan(StringUtils.toStringArray(packageToScan));
    }

    private Set<String> getPackageToScan(AnnotationMetadata metadata) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(ComponentScan.class.getName()));
        String[] basePackages = annotationAttributes.getStringArray("basePackages");
        Class<?>[] basePackageClasses = annotationAttributes.getClassArray("basePackageClasses");
        String[] values = annotationAttributes.getStringArray("value");
        Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(values));
        packagesToScan.addAll(Arrays.asList(basePackages));
        for (Class<?> basePackageClass : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }
        if (packagesToScan.isEmpty()) {
            return Collections.singleton(ClassUtils.getPackageName(metadata.getClassName()));
        }
        return packagesToScan;
    }

    private ClassPathBeanDefinitionScanner getScanner(BeanDefinitionRegistry registry) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        TypeFilter filter1 = new AssignableTypeFilter(Hello.class);
        TypeFilter filter = new AnnotationTypeFilter(Component.class);
        scanner.addIncludeFilter(filter1);
        scanner.addIncludeFilter(filter);
        return scanner;
    }
}
