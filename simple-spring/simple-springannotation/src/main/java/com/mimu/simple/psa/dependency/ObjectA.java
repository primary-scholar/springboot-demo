package com.mimu.simple.psa.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/10/17
 */

/**
 * 对于 @Autowired 注解的属性，spring 在解析时 是使用 AutowiredAnnotationBeanPostProcessor 进行解析，
 * 解析过程 是使用反射 获取到该类中的 field 属性和 method 属性，封装成 AnnotatedFieldElement 和 AnnotatedMethodElement
 * 然后调用反射 field.set() 和 method.invoke()  进行属性值的 填充
 */
@Component
public class ObjectA {
    @Autowired
    private DependencyA dependencyA;
    private DependencyB dependencyB;

    @Autowired
    public void setDependencyB(DependencyB dependencyB) {
        this.dependencyB = dependencyB;
    }

    @Override
    public String toString() {
        return "ObjectA{" +
                "dependencyA=" + dependencyA +
                ", dependencyB=" + dependencyB +
                '}';
    }
}
