package com.mimu.simple.spring.mybatis.generator.mapper;

import com.mimu.simple.spring.mybatis.generator.model.TermInfo;
import com.mimu.simple.spring.mybatis.generator.model.TermInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TermInfoMapper {
    long countByExample(TermInfoExample example);

    int deleteByExample(TermInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TermInfo record);

    int insertSelective(TermInfo record);

    List<TermInfo> selectByExample(TermInfoExample example);

    TermInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TermInfo record, @Param("example") TermInfoExample example);

    int updateByExample(@Param("record") TermInfo record, @Param("example") TermInfoExample example);

    int updateByPrimaryKeySelective(TermInfo record);

    int updateByPrimaryKey(TermInfo record);
}