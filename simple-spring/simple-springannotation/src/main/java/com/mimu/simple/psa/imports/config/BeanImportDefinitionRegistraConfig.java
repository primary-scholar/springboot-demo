package com.mimu.simple.psa.imports.config;

import com.mimu.simple.psa.imports.registrar.BeanImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.mimu.simple.psa.imports.beans")
@Import(BeanImportBeanDefinitionRegistrar.class)
public class BeanImportDefinitionRegistraConfig {
}
