package com.mimu.simple.springboot.dubbo.api;


import com.mimu.simple.springboot.dubbo.model.HelloData;

/**
 * author: mimu
 * date: 2019/9/18
 */
public interface HelloStringApi {
    HelloData hello(String name) throws Exception;
}
