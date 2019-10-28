package com.mimu.simple.springboot.dubbo.api;


import com.mimu.simple.springboot.dubbo.model.UserData;

/**
 * author: mimu
 * date: 2019/8/18
 */
public interface UserDataApi {

    UserData getUserData(long pid);
}
