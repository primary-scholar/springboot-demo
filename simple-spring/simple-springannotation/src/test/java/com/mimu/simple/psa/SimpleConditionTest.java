package com.mimu.simple.psa;

import com.mimu.simple.psa.config.SimpleConditionAnnotationConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: mimu
 * date: 2019/7/26
 */
public class SimpleConditionTest {
    private static final Logger logger = LoggerFactory.getLogger(SimpleConditionTest.class);

    @Test
    public void info() {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(SimpleConditionAnnotationConfig.class);
        configApplicationContext.refresh();
        for (String s : configApplicationContext.getBeanDefinitionNames()) {
            logger.info(s);
        }
        configApplicationContext.getEnvironment().getSystemProperties().forEach((key, value) -> logger.info(key + "=" + value));
    }
}
