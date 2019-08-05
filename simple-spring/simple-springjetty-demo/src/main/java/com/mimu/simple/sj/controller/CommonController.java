package com.mimu.simple.sj.controller;

import com.mimu.simple.common.core.Info;
import com.mimu.simple.sj.model.UserData;
import com.mimu.simple.sj.request.UserDataRequest;
import com.mimu.simple.sj.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@RestController
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    private CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(value = "/sj/info.go")
    public Object getInfo(UserDataRequest request) {
        try {
            UserData personData = commonService.getuserData(request);
            logger.info("getInfo result={}",personData);
            return personData;
        } catch (Exception e) {
            Info info = new Info();
            info.setCode(500);
            info.setDesc("server error");
            logger.error("simpleserver3 error", e);
            return info;
        }
    }
}
