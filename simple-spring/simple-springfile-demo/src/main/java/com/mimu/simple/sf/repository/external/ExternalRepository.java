package com.mimu.simple.sf.repository.external;

import com.mimu.simple.sf.model.ObjectAFieldFirst;
import com.mimu.simple.sf.repository.BaseRepository;

/**
 * author: mimu
 * date: 2019/1/12
 */
public class ExternalRepository extends BaseRepository {

    public ObjectAFieldFirst getExternal(int id){
        return get(id);
    }
}
