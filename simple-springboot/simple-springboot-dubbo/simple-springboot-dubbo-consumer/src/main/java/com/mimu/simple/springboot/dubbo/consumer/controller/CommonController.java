package com.mimu.simple.springboot.dubbo.consumer.controller;

import com.mimu.simple.springboot.dubbo.model.UserData;
import com.mimu.simple.springboot.dubbo.consumer.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: mimu
 * date: 2019/10/12
 */
@Slf4j
@RestController
public class CommonController {

    private CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(value = "/sbc/info.go", method = RequestMethod.GET)
    public UserData getUserData(long pid) {
        UserData userData = commonService.getUserData(pid);
        log.info("userInfo={}", userData);
        return userData;
    }
}
