package com.mimu.simple.sf.repository.inner;

import com.mimu.simple.sf.model.Student;
import com.mimu.simple.sf.repository.BaseRepository;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class InnerRepository extends BaseRepository {

    public Student getInner(int id){
        return get(id);
    }
}
