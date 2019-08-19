package com.mimu.simple.sf.repository;

import com.mimu.simple.sf.model.Student;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class BaseRepository {

    protected Student get(int id) {
        Student student = new Student();
        student.setId(id);
        student.setName("file imports: " + id);
        student.setAvator("file imports");
        return student;
    }
}
