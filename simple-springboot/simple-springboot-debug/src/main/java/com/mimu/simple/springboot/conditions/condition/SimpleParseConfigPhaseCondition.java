package com.mimu.simple.springboot.conditions.condition;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;

/**
 author: mimu
 date: 2020/5/18
 */
public class SimpleParseConfigPhaseCondition extends AnyNestedCondition {
    public SimpleParseConfigPhaseCondition() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }

}
