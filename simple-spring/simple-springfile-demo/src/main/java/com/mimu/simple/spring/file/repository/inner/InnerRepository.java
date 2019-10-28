package com.mimu.simple.spring.file.repository.inner;

import com.mimu.simple.spring.file.model.ObjectAFieldFirst;
import com.mimu.simple.spring.file.repository.BaseRepository;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class InnerRepository extends BaseRepository {

    public ObjectAFieldFirst getInner(int id){
        return get(id);
    }
}
