package com.mimu.simple.springboot.mybatis.generator.mapper;

import com.mimu.simple.springboot.mybatis.generator.model.TermInfo;


public interface TermInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TermInfo record);

    int insertSelective(TermInfo record);

    TermInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TermInfo record);

    int updateByPrimaryKey(TermInfo record);
}