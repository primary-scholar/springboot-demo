package com.mimu.simple.spring.mybatis.generator.mapper;

import com.mimu.simple.spring.mybatis.generator.model.termInfo;
import com.mimu.simple.spring.mybatis.generator.model.termInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface termInfoMapper {
    long countByExample(termInfoExample example);

    int deleteByExample(termInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(termInfo record);

    int insertSelective(termInfo record);

    List<termInfo> selectByExample(termInfoExample example);

    termInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") termInfo record, @Param("example") termInfoExample example);

    int updateByExample(@Param("record") termInfo record, @Param("example") termInfoExample example);

    int updateByPrimaryKeySelective(termInfo record);

    int updateByPrimaryKey(termInfo record);
}