package com.mimu.simple.spring.annotation.imports.config;

import com.mimu.simple.spring.annotation.imports.beans.HelloA;
import com.mimu.simple.spring.annotation.imports.beans.HelloB;
import com.mimu.simple.spring.annotation.imports.registrar.BeanImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = {HelloA.class, HelloB.class})
@Import(BeanImportBeanDefinitionRegistrar.class)
public class BeanImportDefinitionRegistrarConfig {
}
