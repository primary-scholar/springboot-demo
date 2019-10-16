package com.mimu.simple.sf.repository;


import com.mimu.simple.sf.model.ObjectAFieldFirst;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class CommonRepository extends BaseRepository {

    public ObjectAFieldFirst getCommon(int id){
        return get(id);
    }
}
