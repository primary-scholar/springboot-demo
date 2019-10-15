package com.mimu.simple.sd.provider.controller;

import com.mimu.simple.sd.api.HelloStringApi;
import com.mimu.simple.sd.api.UserDataApi;
import com.mimu.simple.sd.core.model.HelloData;
import com.mimu.simple.sd.core.model.UserData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Resource(name = "helloStringXImpl")
    public void setHelloStringApi(HelloStringApi helloStringApi) {
        this.helloStringApi = helloStringApi;
    }

    @RequestMapping(value = "/sd/info.go", method = RequestMethod.GET)
    public UserData getUserData(long pid) {
        UserData userData = userDataApi.getUserData(pid);
        log.info("userInfo={}", userData);
        return userData;
    }

    @RequestMapping(value = "/sds/hello.go", method = RequestMethod.GET)
    public HelloData getHello(String name) {
        log.info("helloInfo={}", name);
        return helloStringApi.hello(name);
    }
}
