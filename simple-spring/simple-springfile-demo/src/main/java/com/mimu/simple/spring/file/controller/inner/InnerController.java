package com.mimu.simple.spring.file.controller.inner;

import com.mimu.simple.spring.file.service.inner.InnerService;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class InnerController {

    private InnerService innerService;

    public void setInnerService(InnerService innerService) {
        this.innerService = innerService;
    }

    public void message(){innerService.message();}

}
