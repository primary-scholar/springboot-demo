package com.mimu.simple.spring.annotation.demo;

import com.mimu.simple.spring.annotation.demo.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: mimu
 * date: 2019/1/10
 */
@RestController
public class CommonController {

    private CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }


}
