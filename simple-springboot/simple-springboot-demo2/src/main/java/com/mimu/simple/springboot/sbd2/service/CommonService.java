package com.mimu.simple.springboot.sbd2.service;

import com.mimu.simple.springboot.sbd2.dao.TermRepository;
import com.mimu.simple.springboot.sbd2.dao.UserRepository;
import com.mimu.simple.springboot.sbd2.model.TermData;
import com.mimu.simple.springboot.sbd2.model.UserData;
import com.mimu.simple.springboot.sbd2.request.UserDataRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author: mimu
 * date: 2019/7/5
 */
@Service
public class CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

    private TermRepository termRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTermRepository(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    public UserData getUserDataFrom(long pid) {
        return userRepository.getUserData(pid);
    }

    public TermData getTermDataFrom(long pid) {
        return termRepository.getTermData(pid);
    }

    @Transactional(transactionManager = "userTxManager", rollbackFor = RuntimeException.class)
    public boolean updateInfo(long pid, String nickName, int termId) {
        return addTermData1(pid, termId) && addUserData(pid, nickName);
    }

    public UserData getUserInfo(UserDataRequest request) {
        UserData userData = userRepository.getUserData(request.getPid());
        logger.info("{}", userData);
        return userData;
    }

    private boolean addTermData(long pid, int termId) {
        if (!termRepository.addTermData(pid, termId)) {
            throw new RuntimeException("insert termData error");
        } else {
            return true;
        }
    }

    private boolean addUserData(long pid, String nickName) {
        if (!userRepository.addUserData(pid, nickName)) {
            throw new RuntimeException("insert termData error");
        } else {
            return true;
        }
    }

    private boolean addTermData1(long pid, int termId) {
        if (!userRepository.addTermData(pid, termId)) {
            throw new RuntimeException("insert termData error");
        } else {
            return true;
        }
    }


}
