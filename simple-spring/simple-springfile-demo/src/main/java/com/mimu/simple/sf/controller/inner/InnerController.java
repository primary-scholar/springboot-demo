package com.mimu.simple.sf.controller.inner;

import com.mimu.simple.sf.service.inner.InnerService;

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
