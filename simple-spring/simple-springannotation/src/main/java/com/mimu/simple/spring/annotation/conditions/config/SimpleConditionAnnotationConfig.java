package com.mimu.simple.spring.annotation.conditions.config;

import com.mimu.simple.spring.annotation.conditions.condition.SimpleConditionA;
import com.mimu.simple.spring.annotation.conditions.condition.SimpleConditionB;
import com.mimu.simple.spring.annotation.conditions.model.ConditionBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/7/26
 */

/**
 * 可以和 springboot-demo 中 springboot-debug-running 中的 {@link com.mimu.simple.springboot.conditions.config} 注解进行对比
 *
 * @Configuration 对于 spring container  来说 被该注解标注的类是一个配置文件 类似于 xml 文件
 * @Configuration 解析过程 该过程由 ConfigurationClassPostProcessor 来进行，解析的结果是先生成 ConfigurationClass 对象，注册到 DefaultListableBeanFactory 中,该对象中包含了 beanMethod 信息
 * 1. 对于 使用 @Configuration 标注的嵌套 类(例如本类中的SimpleConditionAConfig) 会进行递归处理
 * 2. 根据 @Configuration 的类定义 获取类中的是 方法 保存到 ConfigurationClass 对象中的 beanMethod中(Map<methodName，Method></>)
 * 3. BeanDefinition 的注册过程：
 * 根据 2.中的 configuration 类中 生成 BeanDefinition
 * 3.1 把 beanMethod 中的 方法名当作 beanDefinitionName Method 当作 BeanDefinition 注册到 DefaultListableBeanFactory 中
 * <p>
 * 生成 singleton 对象时, 会根据 BeanDefinition 定义中的 @Condition 来判断是否跳过该对象的创建
 * <p>
 * ps: 对于 该类的解析过程 会生成 两个 ConfigurationClass (1. SimpleConditionAnnotationConfig；2.SimpleConditionAConfig)
 * 并且 1. 中有两个 beanMethod  (1.conditionB(); 2. conditionAB()),
 * 2. 中有一个 beanMethod (1. conditionA() )
 */
@Configuration
public class SimpleConditionAnnotationConfig {

    /**
     * 嵌套的config class 跟外层的 @configuration 标注的类解析是一样的
     */
    @Configuration
    static class SimpleConditionAConfig {
        @Bean
        @Conditional(SimpleConditionA.class)
        public ConditionBean conditionA() {
            return ConditionBean.builder().flag("condition a").build();
        }

    }

    @Bean
    @Conditional(SimpleConditionB.class)
    public ConditionBean conditionB() {
        return ConditionBean.builder().flag("condition b").build();
    }

    /**
     * @return
     * @conditional 注解中包含多个 condition 实现类时取 && 操作
     */
    @Bean
    @Conditional({SimpleConditionA.class, SimpleConditionB.class})
    public ConditionBean conditionAB() {
        return ConditionBean.builder().flag("condition a and b").build();
    }
}
