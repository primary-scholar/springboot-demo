package com.mimu.simple.psa.imports.selectors;

import com.mimu.simple.psa.imports.beans.HelloA;
import com.mimu.simple.psa.imports.beans.HelloB;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class BeanImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloA.class.getName(), HelloB.class.getName()};
    }
}
