package com.mimu.simple.spring.mybatis.generator;

import com.mimu.simple.spring.mybatis.generator.mapper.TermInfoMapper;
import com.mimu.simple.spring.mybatis.generator.mapper.UserInfoMapper;
import com.mimu.simple.spring.mybatis.generator.model.TermInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/10/30
 */
@Service
public class CommonService {

    private UserInfoMapper userInfoMapper;
    private TermInfoMapper termInfoMapper;

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Autowired
    public void setTermInfoMapper(TermInfoMapper termInfoMapper) {
        this.termInfoMapper = termInfoMapper;
    }

    public long countByExample(int id){
        TermInfoExample termInfoExample = new TermInfoExample();
        termInfoExample.or().andIdEqualTo(id);
        return termInfoMapper.countByExample(termInfoExample);
    }
}
