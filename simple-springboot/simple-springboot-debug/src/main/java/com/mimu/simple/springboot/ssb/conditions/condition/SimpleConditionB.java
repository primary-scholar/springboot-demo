package com.mimu.simple.springboot.ssb.conditions.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * author: mimu
 * date: 2019/7/26
 */
public class SimpleConditionB implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        return Objects.requireNonNull(environment.getProperty("os.name")).toLowerCase().contains("mac os");
    }
}
