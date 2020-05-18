package com.mimu.simple.springboot.conditions.config.condition;

import com.mimu.simple.springboot.conditions.condition.SimpleConditionA;
import com.mimu.simple.springboot.conditions.condition.SimpleConditionB;
import com.mimu.simple.springboot.conditions.condition.SimpleRegistBeanPhaseCondition;
import com.mimu.simple.springboot.conditions.depen.ConditionDependencyBean;
import com.mimu.simple.springboot.conditions.model.ConditionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/7/26
 */

/**
 * 如果 @ComponentScan 中 basePackages 的目录不正确 则不会生成 conditionB 的 实例
 * 因为 conditionB() 方法上使用了 @ConditionOnBean 的注解
 * 对于 具有参数的 方法 conditionB(ConditionDependencyBean conditionDependencyBean) 会使用 自动注入的方式从
 * DefaultListableBeanFactory 中获取参数实例，然后反射调用该方法生成 对象
 */
@Configuration
@ComponentScan(basePackages = {"com.mimu.simple.springboot.conditions.model"})
public class SimpleConditionRegisterBeanPhaseConfig {
    private static Logger logger = LoggerFactory.getLogger(SimpleConditionRegisterBeanPhaseConfig.class);

    /**
     * 嵌套的config class 跟外层的 @configuration 标注的类解析是一样的 是在 processMemberClasses
     * 中进行处理的
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
    @ConditionalOnBean(ConditionDependencyBean.class)
    public ConditionBean conditionB(ConditionDependencyBean conditionDependencyBean, ConditionDependencyBean conditionDependencyBean1) {
        logger.info("conditionB={}", conditionDependencyBean);
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

    @Bean
    @Conditional(SimpleRegistBeanPhaseCondition.class)
    public ConditionBean conditionRegisterBeanPhaseBean() {
        return ConditionBean.builder().flag("simple register bean phase conditionRegisterBeanPhaseBean").build();
    }
}
