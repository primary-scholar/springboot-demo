package com.mimu.simple.springboot.demo.controller;

import com.mimu.simple.springboot.demo.model.UserInfo;
import com.mimu.simple.springboot.demo.request.UserInfoRequest;
import com.mimu.simple.springboot.demo.service.CommonService;
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

    @RequestMapping(value = "/sb/info.go", method = RequestMethod.GET)
    public UserInfo getUserData(UserInfoRequest request, long cid) {
        UserInfo userInfo = commonService.getUserInfo(request);
        logger.info("request={},cid={}", request, cid);
        return userInfo;
    }
}
