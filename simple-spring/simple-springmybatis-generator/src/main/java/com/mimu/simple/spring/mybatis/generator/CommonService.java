package com.mimu.simple.spring.mybatis.generator;

import com.mimu.simple.spring.mybatis.generator.mapper.CourseInfoMapper;
import com.mimu.simple.spring.mybatis.generator.mapper.SchoolInfoMapper;
import com.mimu.simple.spring.mybatis.generator.model.CourseInfo;
import com.mimu.simple.spring.mybatis.generator.model.CourseInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author: mimu
 * date: 2019/10/30
 */
@Service
public class CommonService {

    private CourseInfoMapper courseInfoMapper;
    private SchoolInfoMapper schoolInfoMapper;

    @Autowired
    public void setCourseInfoMapper(CourseInfoMapper courseInfoMapper) {
        this.courseInfoMapper = courseInfoMapper;
    }

    @Autowired
    public void setSchoolInfoMapper(SchoolInfoMapper schoolInfoMapper) {
        this.schoolInfoMapper = schoolInfoMapper;
    }

    public long countByExample(int id) {
        CourseInfoExample termInfoExample = new CourseInfoExample();
        return courseInfoMapper.countByExample(termInfoExample);
    }

    public List<CourseInfo> find() {
        CourseInfoExample example = new CourseInfoExample();
        example.setDistinct(true);
        example.setOrderByClause("person_id desc,term_id");
        List<Integer> personIdList = new ArrayList<>();
        personIdList.add(10);
        personIdList.add(13);
        personIdList.add(15);
        personIdList.add(20);
        example.or().andNoIn(personIdList).andHourBetween(9, 19).andIdGreaterThan(3);
        return courseInfoMapper.selectByExample(example);
    }

}
