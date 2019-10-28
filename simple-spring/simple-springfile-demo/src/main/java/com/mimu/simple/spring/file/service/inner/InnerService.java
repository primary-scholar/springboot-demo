package com.mimu.simple.spring.file.service.inner;

import com.mimu.simple.spring.file.repository.inner.InnerRepository;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class InnerService {

    private InnerRepository innerRepository;

    public void setInnerRepository(InnerRepository innerRepository) {
        this.innerRepository = innerRepository;
    }

    public void info() {
        int id = 100;
        System.out.println(innerRepository.getInner(id));
    }

    public void message() {
        int id = 2;
        System.out.println(innerRepository.getInner(id));
    }

}
