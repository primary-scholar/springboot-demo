package com.mimu.simple.psa.config;

import com.mimu.simple.psa.condition.SimpleConditionA;
import com.mimu.simple.psa.condition.SimpleConditionB;
import com.mimu.simple.psa.model.ConditionBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/7/26
 */
@Configuration
public class SimpleConditionAnnotationConfig {

    @Bean
    @Conditional(SimpleConditionA.class)
    public ConditionBean conditionA() {
        return ConditionBean.builder().flag("condition a").build();
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
