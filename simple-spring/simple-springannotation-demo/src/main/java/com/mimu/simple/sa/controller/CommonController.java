package com.mimu.simple.sa.controller;

import com.mimu.simple.sa.core.SimpleController;
import com.mimu.simple.sa.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author: mimu
 * date: 2019/1/10
 */
@SimpleController
public class CommonController {

    private CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }


}
