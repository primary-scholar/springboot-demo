package com.mimu.simple.spring.file.repository;

import com.mimu.simple.spring.file.model.ObjectAFieldFirst;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class BaseRepository {

    protected ObjectAFieldFirst get(int id) {
        ObjectAFieldFirst objectAFieldFirst = new ObjectAFieldFirst();
        return objectAFieldFirst;
    }
}
