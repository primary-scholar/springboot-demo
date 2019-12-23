package com.mimu.simple.spring.dubbo.consumer.service;

import com.mimu.simple.spring.dubbo.api.UserDataApi;
import com.mimu.simple.spring.dubbo.model.UserData;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/8/18
 */

@Service
public class CommonService {

    private UserDataApi userDataApi;

    @Reference(check = false, retries = 0,version = "*")
    /**
     * 可以使用直联方式 进行服务的消费，一般用于测试环境测试接口的可用性，直联方式不依赖zk的参与
     */
    //@Reference(check = false, retries = 0, url = "META-INF://10.2.132.47:20880")
    public void setUserDataApi(UserDataApi userDataApi) {
        this.userDataApi = userDataApi;
    }


    public UserData getUserData(long pid) {
        return userDataApi.getUserData(pid);
    }
}
