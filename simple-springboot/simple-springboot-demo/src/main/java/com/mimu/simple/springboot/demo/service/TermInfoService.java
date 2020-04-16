package com.mimu.simple.springboot.demo.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 author: mimu
 date: 2020/4/16
 */
@Service
public class TermInfoService {

    @Cacheable
    public void getInfo() {

    }

    @CachePut
    public boolean updateInfo() {
        return false;
    }
}
