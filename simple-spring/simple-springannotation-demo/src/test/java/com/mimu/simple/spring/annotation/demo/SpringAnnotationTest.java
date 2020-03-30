package com.mimu.simple.spring.annotation.demo;


import com.mimu.simple.spring.annotation.demo.config.ApplicationConfig;
import com.mimu.simple.spring.annotation.demo.model.PersonData;
import com.mimu.simple.spring.annotation.demo.model.TermData;
import com.mimu.simple.spring.annotation.demo.service.CommonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

public class SpringAnnotationTest {

    @Test
    public void info() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.refresh();
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Test
    public void info1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.refresh();
        CommonService commonService = context.getBean(CommonService.class);
        commonService.saveInfo(new PersonData(1,""),new TermData());
        System.out.println(commonService.getPeople(1));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.refresh();
    }

}
