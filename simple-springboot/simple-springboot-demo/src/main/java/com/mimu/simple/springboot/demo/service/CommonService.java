package com.mimu.simple.springboot.demo.service;

import com.mimu.simple.springboot.demo.dao.TermInfoRepository;
import com.mimu.simple.springboot.demo.dao.UserInfoRepository;
import com.mimu.simple.springboot.demo.model.TermInfo;
import com.mimu.simple.springboot.demo.model.UserInfo;
import com.mimu.simple.springboot.demo.request.UserInfoRequest;
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

    private TermInfoRepository termInfoRepository;
    private UserInfoRepository userInfoRepository;

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setTermInfoRepository(TermInfoRepository termInfoRepository) {
        this.termInfoRepository = termInfoRepository;
    }

    public UserInfo getUserInfo(int pid) {
        return userInfoRepository.getUserInfo(pid);
    }

    public TermInfo getTermInfo(int pid) {
        return termInfoRepository.getTermInfo(pid);
    }

    @Transactional(transactionManager = "userTxManager", rollbackFor = RuntimeException.class)
    public boolean updateInfo(int pid, String nickName, int termId) {
        return addTermInfo1(pid, termId) && addUserInfo(pid, nickName);
    }

    public UserInfo getUserInfo(UserInfoRequest request) {
        UserInfo userInfo = userInfoRepository.getUserInfo(request.getPid());
        logger.info("{}", userInfo);
        return userInfo;
    }

    private boolean addTermInfo(int pid, int termId) {
        if (!termInfoRepository.addTermInfo(pid, termId)) {
            throw new RuntimeException("insert termData error");
        } else {
            return true;
        }
    }

    private boolean addUserInfo(int pid, String nickName) {
        if (!userInfoRepository.addUserInfo(pid, nickName)) {
            throw new RuntimeException("insert termData error");
        } else {
            return true;
        }
    }

    private boolean addTermInfo1(long pid, int termId) {
        if (!userInfoRepository.addTermInfo(pid, termId)) {
            throw new RuntimeException("insert termData error");
        } else {
            return true;
        }
    }


}
