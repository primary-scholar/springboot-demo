package com.mimu.simple.spring.annotation.autowires;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentA {

    @Autowired
    private DependencyA dependencyA;

    public void dependencyInfo(){
        dependencyA.info();
    }
}
