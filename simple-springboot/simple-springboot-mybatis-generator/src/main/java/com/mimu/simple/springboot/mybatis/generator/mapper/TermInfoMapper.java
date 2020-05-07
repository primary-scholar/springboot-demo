package com.mimu.simple.springboot.mybatis.generator.mapper;

import com.mimu.simple.springboot.mybatis.generator.model.TermInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface TermInfoMapper {
    @Delete({
        "delete from term_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into term_info (person_id, term_id)",
        "values (#{personId,jdbcType=INTEGER}, #{termId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TermInfo record);

    int insertSelective(TermInfo record);

    @Select({
        "select",
        "id, person_id, term_id",
        "from term_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mimu.simple.springboot.mybatis.generator.mapper.TermInfoMapper.BaseResultMap")
    TermInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TermInfo record);

    @Update({
        "update term_info",
        "set person_id = #{personId,jdbcType=INTEGER},",
          "term_id = #{termId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TermInfo record);
}