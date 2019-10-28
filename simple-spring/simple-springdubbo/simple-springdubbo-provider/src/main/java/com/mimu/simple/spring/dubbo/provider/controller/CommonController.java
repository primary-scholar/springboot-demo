package com.mimu.simple.spring.dubbo.provider.controller;

import com.mimu.simple.spring.dubbo.api.HelloStringApi;
import com.mimu.simple.spring.dubbo.api.UserDataApi;
import com.mimu.simple.sd.core.model.HelloData;
import com.mimu.simple.spring.dubbo.model.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author: mimu
 * date: 2019/8/18
 */
@Slf4j
@RestController
public class CommonController {
    private UserDataApi userDataApi;
    private HelloStringApi helloStringApi;

    @Autowired
    public void setUserDataApi(UserDataApi userDataApi) {
        this.userDataApi = userDataApi;
    }

    @Resource(name = "helloStringImpl")
    public void setHelloStringApi(HelloStringApi helloStringApi) {
        this.helloStringApi = helloStringApi;
    }

    @RequestMapping(value = "/sds/info.go", method = RequestMethod.GET)
    public UserData getUserData(long pid) {
        UserData userData = userDataApi.getUserData(pid);
        log.info("userInfo={}", userData);
        return userData;
    }

    @RequestMapping(value = "/sds/hello.go", method = RequestMethod.GET)
    public HelloData getHello(String name) {
        try {
            log.info("helloInfo={}", name);
            return helloStringApi.hello(name);
        } catch (Exception e) {
            log.error("getHello error", e);
            return new HelloData();

        }
    }
}
