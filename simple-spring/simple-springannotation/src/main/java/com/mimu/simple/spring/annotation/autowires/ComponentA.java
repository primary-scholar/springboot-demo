package com.mimu.simple.spring.annotation.autowires;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ComponentA {

    @Autowired
    //@Resource
    private IDependency dependencyA;

    public void dependencyInfo(){
        dependencyA.info();
    }
}
