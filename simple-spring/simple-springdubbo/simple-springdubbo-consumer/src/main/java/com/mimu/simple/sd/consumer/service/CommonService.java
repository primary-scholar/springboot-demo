/*
package com.mimu.simple.sd.consumer.service;

import com.mimu.simple.sd.api.UserDataApi;
import com.mimu.simple.sd.core.model.UserData;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

*/
/**
 * author: mimu
 * date: 2019/8/18
 *//*

@Service
public class CommonService {

    @Reference(check = false)
    private UserDataApi userDataApi;

    */
/*@Reference(check = false)
    public void setUserDataApi(UserDataApi userDataApi) {
        this.userDataApi = userDataApi;
    }*//*


    public UserData getUserData(long pid) {
        return userDataApi.getUserData(pid);
    }
}
*/
