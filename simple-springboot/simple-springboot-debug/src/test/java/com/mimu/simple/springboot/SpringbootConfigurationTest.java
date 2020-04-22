package com.mimu.simple.springboot;

import com.mimu.simple.springboot.configurations.ConfigurationProject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 author: mimu
 date: 2020/4/22
 */
@RunWith(SpringRunner.class)
public class SpringbootConfigurationTest {

    @Test
    public void info() {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(ConfigurationProject.class);
        configApplicationContext.refresh();
        for (String s : configApplicationContext.getBeanDefinitionNames()) {
            System.out.println(configApplicationContext.getBean(s).toString());
        }
    }
}
