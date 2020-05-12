package com.mimu.simple.spring.annotation.dependency;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 author: mimu
 date: 2020/4/26
 */

/**
 * ObjectProvider 与 @Autowire 类似用于依赖注入
 * 1. @Autowire注解可以用于 field,setter,constructor 而 ObjectProvider 主要用于 constructor
 * 2. 当 constructor() 存在多个参数需要依赖注入,则Autowire 无法解决,而 ObjectProvider 可进行多参数注入
 * 3. 被 @Autowire 标注的依赖对象不存在时,则注入过程会出现异常,而 ObjectProvider.getIfAvailable() 可返回 Null
 * 而不报异常
 *
 * 其解析过程：即 被依赖的bean的生成过程 是在 ObjectProvider.getIfAvailable() 中开始的
 * 具体可参考 {@link org.springframework.beans.factory.support.DefaultListableBeanFactory .DependencyObjectProvider.getIfAvailable()}
 * 的执行过程
 */
@Component
public class ObjectB {

    private DependencyA dependencyA;
    private DependencyB dependencyB;

    public ObjectB(ObjectProvider<DependencyA> dependencyAObjectProperty, ObjectProvider<DependencyB> dependencyBObjectProperty) {
        this.dependencyA = dependencyAObjectProperty.getIfAvailable();
        this.dependencyB = dependencyBObjectProperty.getIfAvailable();
    }

    @Override
    public String toString() {
        return "ObjectB{" +
                "dependencyA=" + dependencyA +
                ", dependencyB=" + dependencyB +
                '}';
    }
}
