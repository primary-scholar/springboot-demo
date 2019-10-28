package com.mimu.simple.spring.annotation.conditions.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * author: mimu
 * date: 2019/7/26
 */
public class SimpleConditionB implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        return environment.getProperty("os.name").toLowerCase().contains("mac os");
    }
}
