package com.mimu.simple.spring.dubbo.provider.service;


import com.mimu.simple.spring.dubbo.api.UserDataApi;
import com.mimu.simple.spring.dubbo.model.UserData;
import com.mimu.simple.spring.dubbo.provider.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/8/18
 */

/**
 * 如果使用 spring-META-INF-provider-annotation.xml 文件则需 在service 类上添加
 * META-INF @Service 注解
 */
//@Service(interfaceClass = UserDataApi.class, timeout = 10)
@Component
public class UserDataApiImpl implements UserDataApi {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserData getUserData(long pid) {
        return userRepository.getUserData(pid);
    }
}
