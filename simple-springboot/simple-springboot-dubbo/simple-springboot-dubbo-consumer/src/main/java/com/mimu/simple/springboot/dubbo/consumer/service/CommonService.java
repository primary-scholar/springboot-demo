package com.mimu.simple.springboot.dubbo.consumer.service;

import com.mimu.simple.springboot.dubbo.api.UserDataApi;
import com.mimu.simple.springboot.dubbo.model.UserData;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/8/18
 */
@Service
public class CommonService {

    @Reference(check = false)
    private UserDataApi userDataApi;

    public UserData getUserData(long pid) {
        return userDataApi.getUserData(pid);
    }
}
