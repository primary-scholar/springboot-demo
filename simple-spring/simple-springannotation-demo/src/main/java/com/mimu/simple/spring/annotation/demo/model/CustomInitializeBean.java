package com.mimu.simple.spring.annotation.demo.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 author: mimu
 date: 2019/12/19
 */

/**
 *  here if you didn't use @Component annotation and any other function to initialization
 *  the bean,it won't be create by spring container and the method afterPropertiesSet won't
 *  be invoked ,
 *  that is to say only the bean instance was instantiate by spring the method afterPropertiesSet
 *  will be invoked
 */
@Slf4j
@Component
public class CustomInitializeBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("method: afterPropertiesSet invoked");
    }
}
