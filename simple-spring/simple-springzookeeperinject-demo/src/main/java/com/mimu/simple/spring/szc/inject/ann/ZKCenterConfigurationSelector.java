package com.mimu.simple.spring.szc.inject.ann;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

/**
 * author: mimu
 * date: 2020/4/27
 */
public class ZKCenterConfigurationSelector extends AdviceModeImportSelector<EnableZKCenter> {

    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        switch (adviceMode) {
            case PROXY:
                return getProxyImports();
            case ASPECTJ:
                return getAspectJImports();
            default:
                return null;
        }
    }

    private String[] getProxyImports() {
        return new String[0];
    }

    private String[] getAspectJImports() {
        return new String[0];
    }
}
