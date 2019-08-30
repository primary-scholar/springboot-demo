package com.mimu.simple.ssh.service;

import com.mimu.simple.ssh.dao.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/8/30
 */
@Service
public class CommonService {

    private CommonRepository commonRepository;

    @Autowired
    public void setCommonRepository(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
    }

    public String getInfo(String s) {
        return commonRepository.getRemoteInfo(s);
    }
}
