package com.mimu.simple.spring.file.repository.external;

import com.mimu.simple.spring.file.model.ObjectAFieldFirst;
import com.mimu.simple.spring.file.repository.BaseRepository;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class ExternalRepository extends BaseRepository {

    public ObjectAFieldFirst getExternal(int id){
        return get(id);
    }
}
