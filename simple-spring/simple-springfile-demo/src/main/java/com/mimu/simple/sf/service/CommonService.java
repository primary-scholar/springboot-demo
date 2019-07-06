package com.mimu.simple.sf.service;

import com.mimu.simple.sf.repository.CommonRepository;
import com.mimu.simple.sf.repository.external.ExternalRepository;
import com.mimu.simple.sf.repository.inner.InnerRepository;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class CommonService {

    private CommonRepository commonRepository;
    private InnerRepository innerRepository;
    private ExternalRepository externalRepository;

    public void setCommonRepository(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
    }

    public void setInnerRepository(InnerRepository innerRepository) {
        this.innerRepository = innerRepository;
    }

    public void setExternalRepository(ExternalRepository externalRepository) {
        this.externalRepository = externalRepository;
    }

    public void info(){
        int id = 1;
        System.out.println(commonRepository.getCommon(id));
        System.out.println(innerRepository.getInner(id));
        System.out.println(externalRepository.getExternal(id));
    }
}
