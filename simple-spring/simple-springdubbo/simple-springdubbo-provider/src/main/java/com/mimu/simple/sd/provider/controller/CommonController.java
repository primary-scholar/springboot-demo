package com.mimu.simple.sd.provider.controller;

import com.mimu.simple.sd.api.HelloStringApi;
import com.mimu.simple.sd.api.UserDataApi;
import com.mimu.simple.sd.core.model.HelloData;
import com.mimu.simple.sd.core.model.UserData;
import com.mimu.simple.sd.provider.service.UserDataApiImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * author: mimu
 * date: 2019/8/18
 */

@RestController
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
    private UserDataApi userDataApi;
    private HelloStringApi helloStringApi;

    @Autowired
    public void setUserDataApi(UserDataApiImpl userDataApi) {
        this.userDataApi = userDataApi;
    }

    @Autowired
    public void setHelloStringApi(HelloStringApi helloStringApi) {
        this.helloStringApi = helloStringApi;
    }

    @RequestMapping(value = "/sd/info.go", method = RequestMethod.GET)
    public UserData getUserData(long pid) {
        UserData userData = userDataApi.getUserData(pid);
        logger.info("userInfo={}", userData);
        return userData;
    }

    @RequestMapping(value = "/sd/hello.go", method = RequestMethod.GET)
    public HelloData getHello(String name) {
        logger.info("helloInfo={}", name);
        return helloStringApi.hello(name);
    }
}
