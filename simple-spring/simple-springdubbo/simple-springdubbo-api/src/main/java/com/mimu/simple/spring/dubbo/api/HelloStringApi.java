package com.mimu.simple.spring.dubbo.api;


import com.mimu.simple.spring.dubbo.model.HelloData;

/**
 * author: mimu
 * date: 2019/9/18
 */
public interface HelloStringApi {
    HelloData hello(String name) throws Exception;

    String hello2(String name);
}
