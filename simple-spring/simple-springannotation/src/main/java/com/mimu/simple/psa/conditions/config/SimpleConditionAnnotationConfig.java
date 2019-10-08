package com.mimu.simple.psa.conditions.config;

import com.mimu.simple.psa.conditions.condition.SimpleConditionA;
import com.mimu.simple.psa.conditions.condition.SimpleConditionB;
import com.mimu.simple.psa.conditions.model.ConditionBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/7/26
 */

/**
 * 可以和 springboot-demo 中 springboot-debug-running 中的 ConditionOnBean 注解进行对比
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
