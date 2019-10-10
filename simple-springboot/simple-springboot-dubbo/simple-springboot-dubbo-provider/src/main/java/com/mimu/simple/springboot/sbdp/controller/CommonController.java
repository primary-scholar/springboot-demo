package com.mimu.simple.springboot.sbdp.controller;

import com.mimu.simple.springboot.sbda.model.UserData;
import com.mimu.simple.springboot.sbdp.service.UserDataApiImpl;
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
    private UserDataApiImpl userDataApi;

    @Autowired
    public void setUserDataApi(UserDataApiImpl userDataApi) {
        this.userDataApi = userDataApi;
    }


    @RequestMapping(value = "/sb3/info.go", method = RequestMethod.GET)
    public UserData getUserData(long pid) {
        UserData userData = userDataApi.getUserData(pid);
        logger.info("userInfo={}", userData);
        return userData;
    }
}
