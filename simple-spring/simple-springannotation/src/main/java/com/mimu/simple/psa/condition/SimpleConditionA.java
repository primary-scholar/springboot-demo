package com.mimu.simple.psa.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Hello world!
 */
public class SimpleConditionA implements Condition {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        return environment.getProperty("os.name").toLowerCase().contains("mac os");
    }
}
