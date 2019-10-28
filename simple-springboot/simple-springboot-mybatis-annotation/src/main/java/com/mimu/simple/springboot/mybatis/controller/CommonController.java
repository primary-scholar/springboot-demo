package com.mimu.simple.springboot.mybatis.controller;

import com.mimu.simple.springboot.mybatis.model.TermData;
import com.mimu.simple.springboot.mybatis.model.UserData;
import com.mimu.simple.springboot.mybatis.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: mimu
 * date: 2019/6/29
 */
@RestController
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
    private CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(value = "/sbma/user/info.go", method = RequestMethod.GET)
    public TermData getTermData(long pid) {
        TermData termData = commonService.getTermData(pid);
        logger.info("userInfo={}", termData);
        return termData;
    }

    @RequestMapping(value = "/sbma/term/info.go", method = RequestMethod.GET)
    public UserData getUserData(long pid) {
        UserData userData = commonService.getUserData(pid);
        logger.info("userInfo={}", userData);
        return userData;
    }

}
