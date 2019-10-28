package com.mimu.simple.spring.file.repository;


import com.mimu.simple.spring.file.model.ObjectAFieldFirst;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class CommonRepository extends BaseRepository {

    public ObjectAFieldFirst getCommon(int id){
        return get(id);
    }
}
