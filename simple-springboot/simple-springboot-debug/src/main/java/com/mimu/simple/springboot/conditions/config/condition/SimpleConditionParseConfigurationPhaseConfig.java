package com.mimu.simple.springboot.conditions.config.condition;

import com.mimu.simple.springboot.conditions.condition.SimpleParseConfigPhaseCondition;
import com.mimu.simple.springboot.conditions.model.ConditionBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 author: mimu
 date: 2020/5/18
 */
@Configuration
@Conditional(SimpleParseConfigPhaseCondition.class)
@ComponentScan(basePackageClasses = ConditionBean.class)
public class SimpleConditionParseConfigurationPhaseConfig {

    @Bean
    public ConditionBean conditionParseConfigurationPhaseBean(){
        return ConditionBean.builder().flag("conditionParseConfigurationPhaseBean").build();
    }
}
