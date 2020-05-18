package com.mimu.simple.springboot.conditions.condition;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;

/**
 author: mimu
 date: 2020/5/18
 */
public class SimpleRegistBeanPhaseCondition extends AnyNestedCondition {
    public SimpleRegistBeanPhaseCondition() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

}
