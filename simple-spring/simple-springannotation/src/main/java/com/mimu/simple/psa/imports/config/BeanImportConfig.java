package com.mimu.simple.psa.imports.config;

import com.mimu.simple.psa.imports.beans.HelloA;
import com.mimu.simple.psa.imports.beans.HelloB;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HelloA.class, HelloB.class})
public class BeanImportConfig {
}
