package com.mimu.simple.springboot.sbda.api;


import com.mimu.simple.springboot.sbda.model.UserData;

/**
 * author: mimu
 * date: 2019/8/18
 */
public interface UserDataApi {

    UserData getUserData(long pid);
}
