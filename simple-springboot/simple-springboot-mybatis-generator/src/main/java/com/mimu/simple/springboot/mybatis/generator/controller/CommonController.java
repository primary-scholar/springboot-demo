package com.mimu.simple.springboot.mybatis.generator.controller;

import com.mimu.simple.springboot.mybatis.generator.model.TermInfo;
import com.mimu.simple.springboot.mybatis.generator.model.UserInfo;
import com.mimu.simple.springboot.mybatis.generator.service.CommonService;
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
    public UserInfo getUerInfo(int pid) {
        UserInfo userInfo = commonService.getUserInfo(pid);
        logger.info("userInfo={}", userInfo);
        return userInfo;
    }

    @RequestMapping(value = "/sbma/term/info.go", method = RequestMethod.GET)
    public TermInfo getUserData(int tid) {
        TermInfo termInfo = commonService.getTermInfo(tid);
        logger.info("userInfo={}", termInfo);
        return termInfo;
    }

}
