package com.mimu.simple.spring.annotation.demo;


import com.mimu.simple.spring.annotation.demo.config.ApplicationConfig;
import com.mimu.simple.spring.annotation.demo.model.PersonData;
import com.mimu.simple.spring.annotation.demo.model.TermData;
import com.mimu.simple.spring.annotation.demo.service.CommonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)*/
public class SpringAnnotationTest {

    public void info() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.refresh();
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.refresh();
        CommonService commonService = context.getBean(CommonService.class);
        commonService.saveInfo(new PersonData(1,""),new TermData());
        System.out.println(commonService.getPeople(1));
    }

}
