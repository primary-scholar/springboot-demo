package com.mimu.simple.spring.annotation.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/10/17
 */

/**
 * 对于 @Autowired 注解的属性，spring 在解析时 是使用 AutowiredAnnotationBeanPostProcessor 进行解析，
 * 解析过程 是使用反射获取到类的元数据信息，对元数据信息进行解析获取 field 属性和 method 属性，封装成
 * AutowiredFieldElement 和 AutowiredMethodElement
 * 然后调用反射 field.set() 和 method.invoke()  进行属性值的 填充
 *
 * 对于 @autowire 注入的非基本数据类型,spring 首先获取到 具体的参数类型，根据参数类型获取到 DefaultListableFactory
 * 中的 匹配的 beanDefinition 然后 获取到 该 beanDefinition 的一个实例 然后 进行 filed.set() 或 method.invoke()
 */
@Component
public class ObjectA {
    //@Autowired
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
