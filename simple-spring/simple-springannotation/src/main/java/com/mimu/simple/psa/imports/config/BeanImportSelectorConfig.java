package com.mimu.simple.psa.imports.config;

import com.mimu.simple.psa.imports.selectors.BeanImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BeanImportSelector.class)
public class BeanImportSelectorConfig {
}
