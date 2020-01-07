package com.mimu.simple.spring.dubbo.consumer.controller;

import com.mimu.simple.spring.dubbo.consumer.service.HelloService;
import com.mimu.simple.spring.dubbo.model.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: mimu
 * date: 2019/10/15
 */
@Slf4j
@RestController
public class CommonController {

    private HelloService helloService;

    @Autowired
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping(value = "/sdc/hello.go", method = RequestMethod.GET)
    public HelloData getHello(String name) {
        log.info("helloInfo={}", name);
        return helloService.getHello(name);
    }

    @RequestMapping(value = "/sdc/hello2.go", method = RequestMethod.GET)
    public String getHello2(String name) {
        log.info("helloInfo={}", name);
        return helloService.getHello2(name);
    }
}
