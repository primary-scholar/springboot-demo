package com.mimu.simple.spring.annotation.imports.selectors;

import com.mimu.simple.spring.annotation.imports.beans.HelloA;
import com.mimu.simple.spring.annotation.imports.beans.HelloB;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class BeanImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloA.class.getName(), HelloB.class.getName()};
    }
}
