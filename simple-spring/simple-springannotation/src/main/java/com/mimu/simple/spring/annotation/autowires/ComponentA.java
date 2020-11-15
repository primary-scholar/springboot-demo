package com.mimu.simple.spring.annotation.autowires;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ComponentA {


    private IDependency dependency;

    @Autowired
    @Qualifier(value = "dependencyA")
    /*@Resource(name = "dependencyB")*/
    public void setDependency(IDependency dependency) {
        this.dependency = dependency;
    }

    public void dependencyInfo(){
        dependency.info();
    }
}
