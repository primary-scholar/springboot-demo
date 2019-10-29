package com.mimu.simple.spring.file.controller;


import com.mimu.simple.spring.file.service.CommonService;
import com.mimu.simple.spring.file.service.external.ExternalService;
import com.mimu.simple.spring.file.service.inner.InnerService;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class CommonController {

    private CommonService commonService;
    private ExternalService externalService;
    private InnerService innerService;


    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }


    public void setExternalService(ExternalService externalService) {
        this.externalService = externalService;
    }


    public void setInnerService(InnerService innerService) {
        this.innerService = innerService;
    }

    public void info(){
        commonService.info();
        externalService.info();
    }
}
