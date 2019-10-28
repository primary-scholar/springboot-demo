package com.mimu.simple.spring.file.controller.external;

import com.mimu.simple.spring.file.service.external.ExternalService;
import com.mimu.simple.spring.file.service.inner.InnerService;

/**
 * author: mimu
 * date: 2019/1/11
 */
public class ExternalController {

    private ExternalService externalService;
    private InnerService innerService;

    public void setExternalService(ExternalService externalService) {
        this.externalService = externalService;
    }

    public void setInnerService(InnerService innerService){
        this.innerService = innerService;
    }

    public void info(){
        externalService.info();
    }
}
