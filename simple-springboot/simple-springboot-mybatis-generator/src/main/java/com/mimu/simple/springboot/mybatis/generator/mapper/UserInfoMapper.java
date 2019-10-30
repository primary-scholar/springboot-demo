package com.mimu.simple.springboot.mybatis.generator.mapper;

import com.mimu.simple.springboot.mybatis.generator.model.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserInfoMapper {
    @Delete({
        "delete from user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_info (id, person_id, ",
        "person_name)",
        "values (#{id,jdbcType=INTEGER}, #{personId,jdbcType=INTEGER}, ",
        "#{personName,jdbcType=VARCHAR})"
    })
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    @Select({
        "select",
        "id, person_id, person_name",
        "from user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mimu.simple.springboot.mybatis.generator.mapper.UserInfoMapper.BaseResultMap")
    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    @Update({
        "update user_info",
        "set person_id = #{personId,jdbcType=INTEGER},",
          "person_name = #{personName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserInfo record);
}