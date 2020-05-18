package com.mimu.simple.springboot.conditions.config;

import com.mimu.simple.springboot.conditions.config.condition.SimpleConditionParseConfigurationPhaseConfig;
import com.mimu.simple.springboot.conditions.config.condition.SimpleConditionRegisterBeanPhaseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 author: mimu
 date: 2020/5/18
 */
@Configuration
//@Import(SimpleConditionRegisterBeanPhaseConfig.class)
@Import(SimpleConditionParseConfigurationPhaseConfig.class)
public class AppConfig {
}
