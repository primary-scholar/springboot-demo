package com.mimu.simple.spring.annotation.imports.registrar;

import com.mimu.simple.spring.annotation.imports.api.Hello;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class BeanImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName());
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        TypeFilter filter = new AssignableTypeFilter(Hello.class);
        scanner.addIncludeFilter(filter);
        Set<String> basepackages = new HashSet<>(Arrays.asList((String[]) annotationAttributes.get("basePackages")));
        if (basepackages.size() <= 0) {
            Class<?>[] classes = (Class<?>[]) annotationAttributes.get("basePackageClasses");
            for (Class<?> aClass : classes) {
                basepackages.add(ClassUtils.getPackageName(aClass));
            }
        }
        scanner.scan(StringUtils.toStringArray(basepackages));
    }
}
