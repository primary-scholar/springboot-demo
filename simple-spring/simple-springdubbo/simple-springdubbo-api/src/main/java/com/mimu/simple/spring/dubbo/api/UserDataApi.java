package com.mimu.simple.spring.dubbo.api;


import com.mimu.simple.spring.dubbo.model.UserData;

/**
 * author: mimu
 * date: 2019/8/18
 */
public interface UserDataApi {
    UserData getUserData(long pid);
}
