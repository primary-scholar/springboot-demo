package com.mimu.simple.spring.mybatis.generator.mapper.school;

import com.mimu.simple.spring.mybatis.generator.model.school.CourseInfo;
import com.mimu.simple.spring.mybatis.generator.model.school.CourseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface CourseInfoMapper {
    long countByExample(CourseInfoExample example);

    int deleteByExample(CourseInfoExample example);

    @Delete({
        "delete from course_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into course_info (no, name, ",
        "hour)",
        "values (#{no,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{hour,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    List<CourseInfo> selectByExample(CourseInfoExample example);

    @Select({
        "select",
        "id, no, name, hour",
        "from course_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mimu.simple.spring.mybatis.generator.mapper.school.CourseInfoMapper.BaseResultMap")
    CourseInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseInfo record, @Param("example") CourseInfoExample example);

    int updateByExample(@Param("record") CourseInfo record, @Param("example") CourseInfoExample example);

    int updateByPrimaryKeySelective(CourseInfo record);

    @Update({
        "update course_info",
        "set no = #{no,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "hour = #{hour,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CourseInfo record);
}