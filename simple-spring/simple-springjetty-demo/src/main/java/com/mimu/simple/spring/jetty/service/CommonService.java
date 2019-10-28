package com.mimu.simple.spring.jetty.service;

import com.mimu.simple.spring.jetty.model.UserData;
import com.mimu.simple.spring.jetty.repository.UserDataRepository;
import com.mimu.simple.spring.jetty.request.UserDataRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/7/15
 */
@Service
public class CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

    private UserDataRepository userDataRepository;

    @Autowired
    public void setUserDataRepository(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public UserData getuserData(UserDataRequest request) {
        return userDataRepository.getData(request.getPid());
    }
}
