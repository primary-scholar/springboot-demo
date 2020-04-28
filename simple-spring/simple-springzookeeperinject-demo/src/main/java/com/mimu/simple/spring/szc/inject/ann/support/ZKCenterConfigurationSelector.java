package com.mimu.simple.spring.szc.inject.ann.support;

import com.mimu.simple.spring.szc.inject.annotation.EnableZKCenter;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;

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
        return new String[]{AutoProxyRegistrar.class.getName(), ProxyZKPropertyConfiguration.class.getName()};
    }

    private String[] getAspectJImports() {
        return new String[0];
    }
}
