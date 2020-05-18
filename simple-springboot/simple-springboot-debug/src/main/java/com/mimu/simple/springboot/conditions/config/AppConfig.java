package com.mimu.simple.springboot.conditions.config;

import com.mimu.simple.springboot.conditions.config.condition.SimpleConditionParseConfigurationPhaseConfig;
import com.mimu.simple.springboot.conditions.config.condition.SimpleConditionRegisterBeanPhaseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.context.annotation.Import;

/**
 author: mimu
 date: 2020/5/18
 */
@Configuration
@Import(value = {SimpleConditionRegisterBeanPhaseConfig.class/*, SimpleConditionParseConfigurationPhaseConfig.class*/})
public class AppConfig {

    /**
     * 在 spring 的 ConfigurationCondition 中共存在 两个 阶段的 condition 判断逻辑
     * 见 {@link ConfigurationCondition.ConfigurationPhase }
     * 1. PARSE_CONFIGURATION; 2.REGISTER_BEAN 与此对应的的 spring 容器对二者的判断逻辑处理 是在
     * 1.1.{@link ConfigurationClassParser#processConfigurationClass() 该方法中的 conditionEvaluator.shouldSkip() }
     * 即在 加载 被 @Configuration 标注的配置类的时候 进行 condition 逻辑判断
     * 2.2. {@link ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsForBeanMethod(),
     * @link org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass()
     * 方法中的conditionEvaluator.shouldSkip() }
     * 即在 生成 bean 实例 的时候 进行 condition 判断
     *
     * 在 1.1 中如果 shouldSkip() 返回 true 则 被 @Configuration 标注的 配置类不会被加载到spring 容器中
     * 在 2.2 中如果 shouldSkip() 返回 true 则 被 @Configuration 标注的 配置类会被加载到spring 容器中
     * 但是 该配置类中 被 @Bean 标注的方法 则会忽略即不会生成 方法对应的 实例
     *
     */
}
