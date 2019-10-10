package com.mimu.simple.springboot.ssbd1.controller;

import com.mimu.simple.springboot.ssbd1.model.UserData;
import com.mimu.simple.springboot.ssbd1.service.CommonService;
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

    private CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(value = "/sb2/info.go", method = RequestMethod.GET)
    public UserData getUserData(long pid) {
        return commonService.getUserData(pid);
    }
}
