package com.mimu.simple.sd.provider.service;


import com.mimu.simple.sd.api.UserDataApi;
import com.mimu.simple.sd.core.model.UserData;
import com.mimu.simple.sd.provider.dao.UserRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/8/18
 */
@Service(interfaceClass = UserDataApi.class, timeout = 5000)
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
