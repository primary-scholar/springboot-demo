package com.mimu.simple.spring.annotation.autowires;

import org.springframework.stereotype.Component;

@Component
public class DependencyB implements IDependency {
    @Override
    public void info() {
        System.out.println("this is dependencyB");
    }
}
