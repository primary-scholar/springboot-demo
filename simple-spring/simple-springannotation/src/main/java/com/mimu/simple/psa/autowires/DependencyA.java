package com.mimu.simple.psa.autowires;

import org.springframework.stereotype.Component;

@Component
public class DependencyA {

    public void info() {
        System.out.println("this is dependencyA");
    }
}
