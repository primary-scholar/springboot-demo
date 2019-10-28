package com.mimu.simple.spring.file.service.external;

import com.mimu.simple.spring.file.repository.external.ExternalRepository;
import com.mimu.simple.spring.file.repository.inner.InnerRepository;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class ExternalService {

    private ExternalRepository externalRepository;
    private InnerRepository innerRepository;

    public void setExternalRepository(ExternalRepository externalRepository) {
        this.externalRepository = externalRepository;
    }

    public void setInnerRepository(InnerRepository innerRepository) {
        this.innerRepository = innerRepository;
    }

    public void info(){
        int id = 1;
        System.out.println(externalRepository.getExternal(id));
        System.out.println(innerRepository.getInner(id));
    }
}
