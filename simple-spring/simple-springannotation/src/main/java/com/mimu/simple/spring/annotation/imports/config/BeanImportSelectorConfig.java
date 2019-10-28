package com.mimu.simple.spring.annotation.imports.config;

import com.mimu.simple.spring.annotation.imports.selectors.BeanImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BeanImportSelector.class)
public class BeanImportSelectorConfig {
}
